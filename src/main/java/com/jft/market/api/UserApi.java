package com.jft.market.api;


import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jft.market.api.ws.UserWS;

public interface UserApi {

	@RequestMapping(value = {"createUser"},
			method = RequestMethod.POST,
			produces = {MediaType.APPLICATION_JSON_VALUE},
			consumes = {MediaType.APPLICATION_JSON_VALUE})
	public
	@ResponseBody
	ResponseEntity createUser(@Valid @RequestBody UserWS productWS, BindingResult bindingResult);

	@RequestMapping(value = {"user/{userId}"},
			method = RequestMethod.GET,
			produces = {MediaType.APPLICATION_JSON_VALUE})
	public
	@ResponseBody
	ResponseEntity readUser(@PathVariable("userId") Integer userId);

	@RequestMapping(value = {"users"},
			method = RequestMethod.GET,
			produces = {MediaType.APPLICATION_JSON_VALUE})
	public
	@ResponseBody
	ResponseEntity readUsers();

	@RequestMapping(value = {"/user/delete/{userId}"},
			method = RequestMethod.POST,
			produces = {MediaType.APPLICATION_JSON_VALUE})
	public
	@ResponseBody
	ResponseEntity deleteUser(@PathVariable("userId") Integer userId);
}
