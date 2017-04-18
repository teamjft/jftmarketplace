package com.jft.market.service;

import java.util.List;

import com.jft.market.api.ws.CategoryWS;
import com.jft.market.model.Category;

public interface SubCategoryService {

	public String createSubCategory(CategoryWS subCategoryWS, String categoryUuid);

	public String associateParentCategoryWithSubCategory(Category parentCategory, CategoryWS subCategoryWS);

	public String saveChildCategory(Category subCategory);

	public List<CategoryWS> readAllSubCategoriesForParentCategory(String parentCategoryUuid);
}
