package com.jft.market.controllers;

import java.util.List;

import javax.validation.Valid;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.jft.market.api.UserApi;
import com.jft.market.api.UserBean;
import com.jft.market.api.ws.Roles;
import com.jft.market.api.ws.UserWS;
import com.jft.market.exceptions.EntityNotFoundException;
import com.jft.market.exceptions.InvalidRequestException;
import com.jft.market.model.User;
import com.jft.market.service.UserService;

@Slf4j
@Controller
public class UserController implements UserApi {

	@Autowired
	private UserService userService;

	@Override
	public ResponseEntity createUser(@Valid @RequestBody UserWS userWS, BindingResult bindingResult) {
		log.info("Validating paylaod");
		if (bindingResult.hasErrors()) {
			throw new InvalidRequestException(bindingResult);
		}
		log.info("Converting WS to Entity");
		User user = userService.convertWStoEntity(userWS);

		userService.addAndSaveRole(user, Roles.ROLE_USER.getName());
		return new ResponseEntity(userWS, HttpStatus.OK);
	}

	@Override
	public ResponseEntity deleteUser(@PathVariable("userId") Integer userId) {
		log.info("Reading user from database");
		UserBean userBean = userService.readUser(userId);
		if (userBean == null) {
			throw new EntityNotFoundException("User not found. Please provide valid user to delete");
		}
		log.info("Deleting user");
		userService.deleteUser(userBean);
		return new ResponseEntity(HttpStatus.OK);
	}

	@Override
	public ResponseEntity readUser(@PathVariable("userId") Integer userId) {
		log.info("Reading user from database");
		UserBean userBean = userService.readUser(userId);
		if (userBean == null) {
			throw new EntityNotFoundException("User not found.");
		}
		UserWS userWS = userService.convertBeanToWS(userBean);
		return new ResponseEntity(userWS, HttpStatus.OK);
	}

	@Override
	public ResponseEntity readUsers() {
		log.info("Reading users from database");
		List<User> users = userService.readUsers();
		if (users.isEmpty()) {
			throw new EntityNotFoundException("No user found");
		}
		List<UserWS> userWSList = userService.convertUsersToUsersWS(users);
		return new ResponseEntity(userWSList, HttpStatus.OK);
	}
}
