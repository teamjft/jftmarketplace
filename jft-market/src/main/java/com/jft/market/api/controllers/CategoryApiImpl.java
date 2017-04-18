package com.jft.market.api.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jft.market.api.ApiConstants;
import com.jft.market.api.BeanAttribute;
import com.jft.market.api.ws.CategoryWS;
import com.jft.market.api.ws.EmberResponse;
import com.jft.market.api.ws.SuccessWS;
import com.jft.market.exceptions.InvalidRequestException;
import com.jft.market.service.CategoryService;
import com.jft.market.service.SubCategoryService;

@RestController
@CrossOrigin
public class CategoryApiImpl implements CategoryAPI {

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private SubCategoryService subCategoryService;

	@Override
	public ResponseEntity createCategory(@Valid @RequestBody CategoryWS categoryWS, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			throw new InvalidRequestException(bindingResult);
		}
		String uuid = categoryService.createCategory(categoryWS);
		BeanAttribute categorytBeanAttribute = new BeanAttribute(uuid, new SuccessWS(ApiConstants.SUCCESS), ApiConstants.CATEGORY);
		return new ResponseEntity(new EmberResponse<>(categorytBeanAttribute), HttpStatus.OK);
	}

	@Override
	public ResponseEntity readCategory(@PathVariable("categoryUuid") String categoryUuid) {
		CategoryWS categoryWS = categoryService.readCategory(categoryUuid);
		BeanAttribute categorytBeanAttribute = new BeanAttribute(categoryWS.getUuid(), categoryWS, ApiConstants.CATEGORY);
		return new ResponseEntity(new EmberResponse<>(categorytBeanAttribute), HttpStatus.OK);
	}

	@Override
	public ResponseEntity readCategories() {
		List<CategoryWS> categories = categoryService.readCategoriesWS();
		List<BeanAttribute> categoryBeanAttributes = new ArrayList<>();
		categories.forEach(categoryWS -> {
			BeanAttribute categorytBeanAttribute = new BeanAttribute(categoryWS.getUuid(), categoryWS, ApiConstants.CATEGORY);
			categoryBeanAttributes.add(categorytBeanAttribute);
		});
		return new ResponseEntity(new EmberResponse<>(categoryBeanAttributes), HttpStatus.OK);
	}

	@Override
	public ResponseEntity updateCategory(@RequestBody CategoryWS categoryWS, @PathVariable("categoryUuid") String categoryUuid) {
		categoryService.updateCategory(categoryWS, categoryUuid);
		BeanAttribute categorytBeanAttribute = new BeanAttribute(categoryUuid, new SuccessWS(ApiConstants.SUCCESS), ApiConstants.CATEGORY);
		return new ResponseEntity(new EmberResponse<>(categorytBeanAttribute), HttpStatus.OK);
	}

	@Override
	public ResponseEntity deleteCategory(@PathVariable("uuid") String uuid) {
		categoryService.deleteCategory(uuid);
		BeanAttribute categorytBeanAttribute = new BeanAttribute(uuid, new SuccessWS(ApiConstants.SUCCESS), ApiConstants.CATEGORY);
		return new ResponseEntity(new EmberResponse<>(categorytBeanAttribute), HttpStatus.OK);
	}

	@Override
	public ResponseEntity createSubCategory(@Valid @RequestBody CategoryWS subCategoryWS, BindingResult bindingResult, @PathVariable("categoryUuid") String categoryUuid) {
		String subCategoryUuid = subCategoryService.createSubCategory(subCategoryWS, categoryUuid);
		BeanAttribute subCategorytBeanAttribute = new BeanAttribute(subCategoryUuid, new SuccessWS(ApiConstants.SUCCESS), ApiConstants.CATEGORY);
		return new ResponseEntity(new EmberResponse<>(subCategorytBeanAttribute), HttpStatus.OK);
	}

	@Override
	public ResponseEntity readAllSubCategoriesForParentCategory(@PathVariable("categoryUuid") String parentcategoryUuid) {
		List<CategoryWS> categories = subCategoryService.readAllSubCategoriesForParentCategory(parentcategoryUuid);
		List<BeanAttribute> categoryBeanAttributes = new ArrayList<>();
		categories.forEach(categoryWS -> {
			BeanAttribute categorytBeanAttribute = new BeanAttribute(categoryWS.getUuid(), categoryWS, ApiConstants.CATEGORY);
			categoryBeanAttributes.add(categorytBeanAttribute);
		});
		return new ResponseEntity(new EmberResponse<>(categoryBeanAttributes), HttpStatus.OK);
	}
}
