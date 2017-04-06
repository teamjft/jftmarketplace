package com.jft.market.service;

import java.util.List;

import com.jft.market.api.ws.UserWS;
import com.jft.market.model.Role;
import com.jft.market.model.User;

public interface UserService {

	public User convertWStoEntity(UserWS userWS);

	public void convertWsToEnityAndSave(UserWS userWS);

	public List<UserWS> convertUsersToUsersWS(List<User> users);

	public List<User> readUsers();

	public List<UserWS> readAllUsers();

	public void saveUser(User user);

	public User readUserByUuid(String useruuid);

	public UserWS convertEntityToWS(User user);

	public void deleteUser(String userUuid);

	public Boolean isValidUser(User user);

	public void validateUserWS(UserWS userWS);

	public void updateUser(User user, UserWS userWS);

	public void updateUserRoles(String userUuid);

	public Role getAdminRole();
}
