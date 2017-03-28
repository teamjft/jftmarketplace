package com.jft.market.service;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jft.market.api.UserBean;
import com.jft.market.api.ws.UserWS;
import com.jft.market.model.Role;
import com.jft.market.model.User;
import com.jft.market.repository.RoleRepository;
import com.jft.market.repository.UserRepository;

@Service("userService")
public class UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;

	@Transactional(readOnly = true)
	public User convertWStoEntity(UserWS userWS) {
		// USER and ROLE entity is populating
		User user = new User();
		if (userWS != null) {
			user.setUsername(userWS.getUsername());
			user.setPassword(userWS.getPassword());
			user.setEmail(userWS.getEmail());
			user.setEnabled(Boolean.TRUE);
			user.setGender(userWS.getGender());

			return user;
		}
		return null;
	}

	public Role findRole(String roleName) {
		System.out.println("nme = " + roleName);
		Role role = roleRepository.findByName(roleName);
		return role;
	}

	public void addAndSaveRole(User user, Role role) {
		user.getRoles().add(role);
		role.getUsers().add(user);
		userRepository.save(user);
	}

	@Transactional
	public void addAndSaveRole(User user, String roleName) {
		Role role = findRole(roleName);
		addAndSaveRole(user, role);
	}

	public User converBeanToEntity(UserBean userBean) {
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

	public UserBean readUser(Integer userId) {
		User user = userRepository.findOne(userId);
		UserBean userBean = createUserBean(user);
		return userBean;
	}

	public List<User> readUsers() {
		return userRepository.findAll();

	}

	public void deleteUser(UserBean userBean) {
		User user = converBeanToEntity(userBean);
		userRepository.delete(user);
	}
}
