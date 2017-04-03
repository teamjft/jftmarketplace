package com.jft.market.service;

import java.util.List;

import com.jft.market.api.UserBean;
import com.jft.market.api.ws.UserWS;
import com.jft.market.model.Role;
import com.jft.market.model.User;

public interface UserService {

	public User convertWStoEntity(UserWS userWS);

	public void convertWsToEnityAndSave(UserWS userWS);

	public Role findRole(String roleName);

	public User convertBeanToEntity(UserBean userBean);

	public UserBean createUserBean(User user);

	public UserWS convertBeanToWS(UserBean userBean);

	public List<UserWS> convertUsersToUsersWS(List<User> users);

	public UserBean readUser(Long userId);

	public List<User> readUsers();

	public List<UserWS> readAllUsers();

	public void saveUser(User user);

	/*public void deleteUser(UserBean userBean);*/

	public User convertWstoEntity(UserWS userWS);

	public UserWS readUserByUuid(String useruuid);

	public UserWS convertEntityToWS(User user);

	public void deleteUser(String userUuid);


}
