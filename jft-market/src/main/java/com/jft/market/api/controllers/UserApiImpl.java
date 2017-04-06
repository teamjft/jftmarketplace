package com.jft.market.api.controllers;

import java.util.List;

import javax.validation.Valid;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jft.market.api.ws.UserWS;
import com.jft.market.exceptions.InvalidRequestException;
import com.jft.market.service.UserService;

@Slf4j
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
		return new ResponseEntity(HttpStatus.OK);
	}

	@Override
	public ResponseEntity readUser(@PathVariable("userUuid") String userUuid) {
		log.info("Reading user from database");
		UserWS userWS = userService.readUserByUuid(userUuid);
		return new ResponseEntity(userWS, HttpStatus.OK);
	}

	@Override
	public ResponseEntity readUsers() {
		log.info("Reading users from database");
		List<UserWS> users = userService.readAllUsers();
		return new ResponseEntity(users, HttpStatus.OK);
	}

	@Override
	public ResponseEntity deleteUser(@PathVariable("userUuid") String userUuid) {
		userService.deleteUser(userUuid);
		return new ResponseEntity(HttpStatus.OK);
	}
}
