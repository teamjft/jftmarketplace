package com.jft.market.service;

import static com.jft.market.model.QCategory.category1;

import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jft.market.api.ws.CategoryWS;
import com.jft.market.exceptions.ExceptionConstants;
import com.jft.market.model.Category;
import com.jft.market.repository.CategoryRepository;
import com.jft.market.util.EntityPredicates;
import com.jft.market.util.Preconditions;
import com.mysema.query.jpa.JPQLQuery;
import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.types.Predicate;

@Service("subCategoryService")
public class SubCategoryServiceImpl implements SubCategoryService {

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private CategoryRepository categoryRepository;

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	@Transactional
	public String createSubCategory(CategoryWS subCategoryWS, String categoryUuid) {
		Category parentCategory = categoryService.readCategoryByUuid(categoryUuid);
		Category childCategory = categoryService.findCategoryByName(subCategoryWS.getName());
		Preconditions.check(parentCategory == null, ExceptionConstants.PLEASE_CREATE_CATEGORY);
		Preconditions.check(childCategory != null, ExceptionConstants.CATEGORY_ALREADY_EXIST);
		return associateParentCategoryWithSubCategory(parentCategory, subCategoryWS);
	}

	@Override
	@Transactional
	public String associateParentCategoryWithSubCategory(Category parentCategory, CategoryWS subCategoryWS) {
		Category childCategory = new Category();
		childCategory.setName(subCategoryWS.getName());
		childCategory.setDescription(subCategoryWS.getDescription());
		parentCategory.getSubCategories().add(childCategory);
		childCategory.setCategory(parentCategory);
		return saveChildCategory(childCategory);

	}

	@Override
	@Transactional
	public String saveChildCategory(Category subCategory) {
		if (StringUtils.isEmpty(subCategory.getUuid())) {
			subCategory.setUuid(UUID.randomUUID().toString());
		}
		categoryRepository.save(subCategory);
		return subCategory.getUuid();
	}

	@Override
	@Transactional
	public List<CategoryWS> readAllSubCategoriesForParentCategory(String parentCategoryUuid) {
		Category category = categoryService.readCategoryByUuid(parentCategoryUuid);
		JPQLQuery query = new JPAQuery(entityManager);
		Predicate validCategories = category1.eq(category).
				and(EntityPredicates.isTimestampedFieldEnabledAndNotDeleted(category1._super));
		List<Category> categories = query.from(category1).where(validCategories).list(category1);
		return categoryService.convertEntityListToWSList(categories);
	}
}
