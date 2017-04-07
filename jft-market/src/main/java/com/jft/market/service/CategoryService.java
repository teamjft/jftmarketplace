package com.jft.market.service;

import java.util.List;

import com.jft.market.api.ws.CategoryWS;
import com.jft.market.model.Category;

public interface CategoryService {

	public void createCategory(CategoryWS categoryWS);

	public Category convertWsToEntity(CategoryWS categoryWS);

	public void saveCategory(Category category);

	public Category readCategoryByUuid(String uuid);

	public CategoryWS readCategory(String uuid);

	public CategoryWS convertEntityToWS(Category category);

	public List<CategoryWS> readCategoriesWS();

	public List<Category> readCategories();

	public List<CategoryWS> convertEntityListToWSList(List<Category> categories);
}
