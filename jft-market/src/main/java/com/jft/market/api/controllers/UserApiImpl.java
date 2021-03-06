package com.jft.market.api.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import lombok.extern.slf4j.Slf4j;

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
import com.jft.market.api.ws.EmberResponse;
import com.jft.market.api.ws.RoleWS;
import com.jft.market.api.ws.SuccessWS;
import com.jft.market.api.ws.UserWS;
import com.jft.market.exceptions.ExceptionConstants;
import com.jft.market.exceptions.InvalidRequestException;
import com.jft.market.service.UserService;
import com.jft.market.util.Preconditions;

@Slf4j
@CrossOrigin
@RestController
public class UserApiImpl implements UserApi {

	@Autowired
	private UserService userService;

	@Override
	public ResponseEntity createUser(@Valid @RequestBody UserWS userWS, BindingResult bindingResult) {
		log.info("Validating paylaod");
		if (bindingResult.hasErrors()) {
			throw new InvalidRequestException(bindingResult);
		}
		log.info("Converting WS to Entity");
		userService.convertWsToEnityAndSave(userWS);
		BeanAttribute userBeanAttribute = new BeanAttribute(ApiConstants.getSucessId(), new SuccessWS(ApiConstants.SUCCESS), ApiConstants.REGISTRATION);
		return new ResponseEntity(new EmberResponse<>(userBeanAttribute), HttpStatus.OK);
	}

	@Override
	public ResponseEntity readUser(@PathVariable("userUuid") String userUuid) {
		log.info("Reading user from database");
		UserWS userWS = userService.readUser(userUuid);
		BeanAttribute userBeanAttribute = new BeanAttribute(userWS.getUuid(), userWS, ApiConstants.REGISTRATION);
		return new ResponseEntity(new EmberResponse<>(userBeanAttribute), HttpStatus.OK);
	}

	@Override
	public ResponseEntity readUsers() {
		log.info("Reading users from database");
		List<UserWS> users = userService.readAllUsers();
		List<BeanAttribute> userBeanAttributes = new ArrayList<>();
		users.forEach(userWS -> {
			BeanAttribute productBeanAttribute = new BeanAttribute(userWS.getUuid(), userWS, ApiConstants.REGISTRATION);
			userBeanAttributes.add(productBeanAttribute);
		});
		return new ResponseEntity(new EmberResponse<>(userBeanAttributes), HttpStatus.OK);
	}

	@Override
	public ResponseEntity deleteUser(@PathVariable("userUuid") String userUuid) {
		userService.deleteUser(userUuid);
		BeanAttribute userBeanAttribute = new BeanAttribute(userUuid, new SuccessWS(ApiConstants.SUCCESS), ApiConstants.REGISTRATION);
		return new ResponseEntity(new EmberResponse<>(userBeanAttribute), HttpStatus.OK);
	}

	@Override
	public ResponseEntity updateUser(@RequestBody UserWS userWS, @PathVariable("userUuid") String userUuid) {
		userService.validateUserWS(userWS);
		userService.updateUser(userWS, userUuid);
		BeanAttribute userBeanAttribute = new BeanAttribute(userUuid, new SuccessWS(ApiConstants.SUCCESS), ApiConstants.REGISTRATION);
		return new ResponseEntity(new EmberResponse<>(userBeanAttribute), HttpStatus.OK);
	}

	@Override
	public ResponseEntity updateUserRole(@RequestBody RoleWS roleWS, @PathVariable("userUuid") String userUuid) {
		List<String> roles = Arrays.asList(roleWS.getRoles());
		Preconditions.check(roles.isEmpty(), ExceptionConstants.NO_ROLE_TO_SAVE);
		userService.updateUserRoles(userUuid);
		BeanAttribute userBeanAttribute = new BeanAttribute(userUuid, new SuccessWS(ApiConstants.SUCCESS), ApiConstants.REGISTRATION);
		return new ResponseEntity(new EmberResponse<>(userBeanAttribute), HttpStatus.OK);
	}
}
