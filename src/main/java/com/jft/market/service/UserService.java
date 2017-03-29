package com.jft.market.service;

import java.util.List;

import com.jft.market.api.UserBean;
import com.jft.market.api.ws.UserWS;
import com.jft.market.model.Role;
import com.jft.market.model.User;

public interface UserService {

	User convertWStoEntity(UserWS userWS);

	Role findRole(String roleName);

	void addAndSaveRole(User user, Role role);

	void addAndSaveRole(User user, String roleName);

	User convertBeanToEntity(UserBean userBean);

	UserBean createUserBean(User user);

	UserWS convertBeanToWS(UserBean userBean);

	List<UserWS> convertUsersToUsersWS(List<User> users);

	UserBean readUser(Integer userId);

	List<User> readUsers();

	void saveUser(User user);

	void deleteUser(UserBean userBean);
}
