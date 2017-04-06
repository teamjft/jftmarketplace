package com.jft.market.service;

import java.util.List;

import com.jft.market.api.ws.UserWS;
import com.jft.market.model.User;

public interface UserService {

	public User convertWStoEntity(UserWS userWS);

	public void convertWsToEnityAndSave(UserWS userWS);

	public List<UserWS> convertUsersToUsersWS(List<User> users);

	public List<User> readUsers();

	public List<UserWS> readAllUsers();

	public void saveUser(User user);

	public UserWS readUserByUuid(String useruuid);

	public UserWS convertEntityToWS(User user);

	public void deleteUser(String userUuid);
}
