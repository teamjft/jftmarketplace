package com.jft.market.api.controllers;


import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jft.market.api.ws.RoleWS;
import com.jft.market.api.ws.UserWS;

@RequestMapping(value = BaseApi.BASE_PATH + UserApi.USER)
public interface UserApi extends BaseApi {

	String USER = "v1/user";
	String USERS = "users";

	@RequestMapping(value = {"create"},
			method = RequestMethod.POST,
			produces = {MediaType.APPLICATION_JSON_VALUE},
			consumes = {MediaType.APPLICATION_JSON_VALUE})
	public
	@ResponseBody
	ResponseEntity createUser(@Valid @RequestBody UserWS userWS, BindingResult bindingResult);

	@RequestMapping(value = {"{userUuid}"},
			method = RequestMethod.GET,
			produces = {MediaType.APPLICATION_JSON_VALUE})
	public
	@ResponseBody
	ResponseEntity readUser(@PathVariable("userUuid") String useruuid);

	@RequestMapping(value = {USERS},
			method = RequestMethod.GET,
			produces = {MediaType.APPLICATION_JSON_VALUE})
	public
	@ResponseBody
	ResponseEntity readUsers();

	@RequestMapping(value = {"delete/{userUuid}"},
			method = RequestMethod.DELETE,
			produces = {MediaType.APPLICATION_JSON_VALUE})
	public
	@ResponseBody
	ResponseEntity deleteUser(@PathVariable("userUuid") String userUuid);

	@RequestMapping(value = {"update/{userUuid}"},
			method = RequestMethod.PATCH,
			produces = {MediaType.APPLICATION_JSON_VALUE},
			consumes = {MediaType.APPLICATION_JSON_VALUE})
	public
	@ResponseBody
	ResponseEntity updateUser(@RequestBody UserWS userWS,
							  @PathVariable("userUuid") String userUuid);


	@RequestMapping(value = {"update/user/{userUuid}/role"},
			method = RequestMethod.PATCH,
			produces = {MediaType.APPLICATION_JSON_VALUE},
			consumes = {MediaType.APPLICATION_JSON_VALUE})
	public
	@ResponseBody
	ResponseEntity updateUserRole(@RequestBody RoleWS roleWS,
								  @PathVariable("userUuid") String userUuid);
}
