package com.jft.market.service;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jft.market.api.UserBean;
import com.jft.market.api.ws.UserWS;
import com.jft.market.model.Role;
import com.jft.market.model.User;
import com.jft.market.repository.RoleRepository;
import com.jft.market.repository.UserRepository;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Override
	@Transactional(readOnly = true)
	public User convertWStoEntity(UserWS userWS) {
		// USER and ROLE entity is populating
		User user = new User();
		if (userWS != null) {
			user.setUsername(userWS.getUsername());
			user.setPassword(passwordEncoder.encode(userWS.getPassword()));
			user.setEmail(userWS.getEmail());
			user.setEnabled(Boolean.TRUE);
			user.setGender(userWS.getGender());

			return user;
		}
		return null;
	}

	@Override
	public Role findRole(String roleName) {
		Role role = roleRepository.findByName(roleName);
		return role;
	}

	@Override
	public void addAndSaveRole(User user, Role role) {
		user.getRoles().add(role);
		role.getUsers().add(user);
		saveUser(user);
	}

	@Override
	@Transactional
	public void addAndSaveRole(User user, String roleName) {
		Role role = findRole(roleName);
		addAndSaveRole(user, role);
	}

	@Override
	public User convertBeanToEntity(UserBean userBean) {
		User user = new User();
		if (userBean != null) {
			user.setUsername(userBean.getUsername());
			user.setPassword(userBean.getPassword());
			user.setEmail(userBean.getEmail());
			user.setId(userBean.getId());
			user.setEnabled(userBean.getEnabled());
			user.setGender(userBean.getGender());

			return user;
		}
		return null;
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

			return userBean;
		}
		return null;
	}

	@Override
	public UserWS convertBeanToWS(UserBean userBean) {
		UserWS userWS = new UserWS();
		if (userBean != null) {
			userWS.setUsername(userBean.getUsername());
			userWS.setPassword(userBean.getPassword());
			userWS.setEmail(userBean.getEmail());
			userWS.setEnabled(userBean.getEnabled());
			userWS.setGender(userBean.getGender());

			return userWS;
		}
		return null;
	}

	@Override
	public List<UserWS> convertUsersToUsersWS(List<User> users) {
		List<UserWS> userWSList = new ArrayList<UserWS>();

		users.forEach(user -> {
			UserWS userWS = new UserWS();
			userWS.setUsername(user.getUsername());
			userWS.setPassword(user.getPassword());
			userWS.setEmail(user.getEmail());
			userWS.setGender(user.getGender());
			userWS.setEnabled(userWS.getEnabled());

			userWSList.add(userWS);
		});

		return userWSList;
	}

	@Override
	public UserBean readUser(Integer userId) {
		User user = userRepository.findOne(userId);
		UserBean userBean = createUserBean(user);
		return userBean;
	}

	@Override
	public List<User> readUsers() {
		return userRepository.findAll();

	}

	@Override
	@Transactional(propagation = Propagation.MANDATORY)
	public void saveUser(User user) {
		if (StringUtils.isEmpty(user.getUuid())) {
			user.setUuid(UUID.randomUUID().toString());
		}
		userRepository.save(user);
	}

	@Override
	public void deleteUser(UserBean userBean) {
		User user = convertBeanToEntity(userBean);
		userRepository.delete(user);
	}
}
