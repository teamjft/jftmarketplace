package com.jft.market.service;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jft.market.api.ws.RoleWS;
import com.jft.market.api.ws.Roles;
import com.jft.market.api.ws.UserWS;
import com.jft.market.exceptions.ExceptionConstants;
import com.jft.market.model.Role;
import com.jft.market.model.User;
import com.jft.market.repository.RoleRepository;
import com.jft.market.repository.UserRepository;
import com.jft.market.util.Preconditions;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;


	@Override
	@Transactional
	public void convertWsToEnityAndSave(UserWS userWS) {
		User user = convertWStoEntity(userWS);
		saveUser(user);
	}

	@Override
	public User convertWStoEntity(UserWS userWS) {
		// USER and ROLE entity is populating
		Preconditions.check(userWS == null, ExceptionConstants.USER_NOT_FOUND);
		User user = new User();
		user.setId(userWS.getId());
		user.setUsername(userWS.getUsername());
		user.setPassword(passwordEncoder.encode(userWS.getPassword()));
		user.setEmail(userWS.getEmail());
		user.setEnabled(Boolean.TRUE);
		user.setGender(userWS.getGender());
		user.setPhone(userWS.getPhone());
		Role role = roleRepository.findByName(Roles.ROLE_USER.getName());
		user.getRoles().add(role);
		role.getUsers().add(user);
		return user;
	}

	@Override
	public List<UserWS> convertUsersToUsersWS(List<User> users) {
		List<UserWS> userWSList = new ArrayList<UserWS>();
		Preconditions.check(users.isEmpty(), ExceptionConstants.USER_NOT_FOUND);
		users.forEach(user -> {
			UserWS userWS = new UserWS();
			userWS.setUsername(user.getUsername());
			userWS.setPassword(user.getPassword());
			userWS.setEmail(user.getEmail());
			userWS.setGender(user.getGender());
			userWS.setEnabled(user.getEnabled());
			userWS.setPhone(user.getPhone());
			user.getRoles().forEach(role -> {
				RoleWS roleWS = new RoleWS();
				roleWS.setName(role.getName());
				userWS.getRoles().add(roleWS);
			});
			userWS.setUuid(user.getUuid());

			userWSList.add(userWS);
		});
		return userWSList;
	}


	@Override
	public List<User> readUsers() {
		return userRepository.findAll();

	}

	@Override
	@Transactional
	public List<UserWS> readAllUsers() {
		List<User> users = readUsers();
		List<User> enabledUsers = new ArrayList<>();
		users.forEach(user -> {
			if(isValidUser(user)){
				enabledUsers.add(user);
			}
		});
		return convertUsersToUsersWS(enabledUsers);
	}

	@Override
	public void saveUser(User user) {
		if (StringUtils.isEmpty(user.getUuid())) {
			user.setUuid(UUID.randomUUID().toString());
		}
		userRepository.save(user);
	}
	@Override
	@Transactional
	public User readUserByUuid(String useruuid) {
		User user = userRepository.findByUuid(useruuid);
		Preconditions.check(user == null, ExceptionConstants.USER_NOT_FOUND);
		Preconditions.check(!isValidUser(user), ExceptionConstants.USER_NOT_ENABLED);
		return user;
	}

	@Override
	public UserWS convertEntityToWS(User user) {
		Preconditions.check(user == null, ExceptionConstants.USER_NOT_FOUND);
		UserWS userWS = new UserWS();
		userWS.setUsername(user.getUsername());
		userWS.setUuid(user.getUuid());
		userWS.setEnabled(user.getEnabled());
		userWS.setGender(user.getGender());
		userWS.setEmail(user.getEmail());
		userWS.setPhone(user.getPhone());
		user.getRoles().forEach(role -> {
			RoleWS roleWS = new RoleWS();
			roleWS.setName(role.getName());
			userWS.getRoles().add(roleWS);
		});
		return userWS;
	}

	@Override
	@Transactional
	public void deleteUser(String userUuid) {
		User user = userRepository.findByUuid(userUuid);
		Preconditions.check(user == null, ExceptionConstants.USER_NOT_FOUND);
		user.setDeleted(Boolean.TRUE);
		user.setEnabled(Boolean.FALSE);
		userRepository.save(user);
	}

	@Override
	public Boolean isValidUser(User user) {
		if (user.getEnabled().equals(Boolean.TRUE) && user.getDeleted().equals(Boolean.FALSE)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void validateUserWS(UserWS userWS) {
		Preconditions.check(StringUtils.isEmpty(userWS.getUsername()), ExceptionConstants.USER_NAME_CANNOT_BE_EMPTY);
		Preconditions.check(StringUtils.isEmpty(userWS.getEmail()), ExceptionConstants.EMAIL_CANNOT_BE_EMPTY);
		Preconditions.check(StringUtils.isEmpty(String.valueOf(userWS.getPhone())), ExceptionConstants.PHONE_NUMBER_CANNOT_BE_EMPTY);
	}

	@Override
	@Transactional
	public void updateUser(User user, UserWS userWS) {
		user.setUsername(userWS.getUsername());
		user.setPhone(userWS.getPhone());
		user.setEmail(userWS.getEmail());
		userRepository.save(user);
	}

	@Override
	@Transactional
	public void updateUserRoles(String userUuid) {
		User user = readUserByUuid(userUuid);
		Role adminRole = getAdminRole();
		user.getRoles().add(adminRole);
		saveUser(user);
	}

	@Override
	@Transactional
	public Role getAdminRole() {
		return roleRepository.findByName(Roles.ROLE_ADMIN.getName());

	}
}











