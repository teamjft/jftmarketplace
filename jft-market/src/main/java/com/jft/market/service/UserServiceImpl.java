package com.jft.market.service;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jft.market.api.UserBean;
import com.jft.market.api.ws.RoleWS;
import com.jft.market.api.ws.Roles;
import com.jft.market.api.ws.UserWS;
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
		Preconditions.check(userWS == null, "User not found");
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
	public UserBean createUserBean(User user) {
		UserBean userBean = new UserBean();
		if (user != null) {
			userBean.setUsername(user.getUsername());
			userBean.setPassword(user.getPassword());
			userBean.setEmail(user.getEmail());
			userBean.setGender(user.getGender());
			userBean.setId(user.getId());
			userBean.setEnabled(user.getEnabled());
			userBean.setUuid(user.getUuid());

			return userBean;
		}
		return null;
	}


	@Override
	public List<UserWS> convertUsersToUsersWS(List<User> users) {
		List<UserWS> userWSList = new ArrayList<UserWS>();
		Preconditions.check(users.isEmpty(), "No user found.");
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
		return convertUsersToUsersWS(users);
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
	public UserWS readUserByUuid(String useruuid) {
		User user = userRepository.findByUuid(useruuid);
		UserWS userWS = convertEntityToWS(user);
		return userWS;
	}

	@Override
	public UserWS convertEntityToWS(User user) {
		Preconditions.check(user == null, "User not found");
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
		Preconditions.check(user == null, "User not found");
		userRepository.delete(user);
	}

	@Override
	@Transactional
	public User readUserByEmail(String email) {
		return userRepository.findByemail(email);
	}
}











