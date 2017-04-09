package com.jft.market.api.controllers;

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

import com.jft.market.api.ws.CategoryWS;
import com.jft.market.api.ws.ResponseStatus;
import com.jft.market.exceptions.InvalidRequestException;
import com.jft.market.service.CategoryService;

@RestController
@CrossOrigin
public class CategoryApiImpl implements CategoryAPI {

	@Autowired
	private CategoryService categoryService;

	@Override
	public ResponseEntity createCategory(@Valid @RequestBody CategoryWS categoryWS, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			throw new InvalidRequestException(bindingResult);
		}
		categoryService.createCategory(categoryWS);
		return ResponseStatus.SUCCESS.getResponse();
	}

	@Override
	public ResponseEntity readCategory(@PathVariable("categoryUuid") String categoryUuid) {
		CategoryWS categoryWS = categoryService.readCategory(categoryUuid);
		return new ResponseEntity(categoryWS, HttpStatus.OK);
	}

	@Override
	public ResponseEntity readCategories() {
		List<CategoryWS> categories = categoryService.readCategoriesWS();
		return new ResponseEntity(categories, HttpStatus.OK);
	}
}
