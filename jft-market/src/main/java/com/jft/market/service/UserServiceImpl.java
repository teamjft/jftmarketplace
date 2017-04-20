package com.jft.market.service;

import static com.jft.market.model.QUser.user;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jft.market.api.ws.Roles;
import com.jft.market.api.ws.UserWS;
import com.jft.market.exceptions.ExceptionConstants;
import com.jft.market.model.Role;
import com.jft.market.model.User;
import com.jft.market.repository.RoleRepository;
import com.jft.market.repository.UserRepository;
import com.jft.market.util.EntityPredicates;
import com.jft.market.util.Preconditions;
import com.mysema.query.jpa.JPQLQuery;
import com.mysema.query.jpa.impl.JPAQuery;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	@PersistenceContext
	private EntityManager entityManager;


	@Override
	@Transactional
	public void convertWsToEnityAndSave(UserWS userWS) {
		User user1 = findByEmail(userWS.getEmail());
		Preconditions.check(user1 != null, ExceptionConstants.USER_ALREADY_EXIST);
		User user = convertWStoEntity(userWS);
		saveUser(user);
	}

	@Override
	public User convertWStoEntity(UserWS userWS) {
		// USER and ROLE entity is populating
		Preconditions.check(userWS == null, ExceptionConstants.USER_NOT_FOUND);
		User user = new User();
		user.setFname(userWS.getFname());
		user.setLname(userWS.getLname());
		user.setPassword(passwordEncoder.encode(userWS.getPassword()));
		user.setEmail(userWS.getEmail());
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
		users.forEach(user -> {
			UserWS userWS = convertEntityToWS(user);
			userWSList.add(userWS);
		});
		return userWSList;
	}

	@Override
	@Transactional
	public List<UserWS> readAllUsers() {
		JPQLQuery query = new JPAQuery(entityManager);
		List<User> users = query.from(user)
				.where(EntityPredicates.isTimestampedFieldEnabledAndNotDeleted(user._super))
				.list(user);
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
		userWS.setFname(user.getFname());
		userWS.setLname(user.getLname());
		userWS.setUuid(user.getUuid());
		userWS.setEnabled(user.getEnabled());
		userWS.setGender(user.getGender());
		userWS.setEmail(user.getEmail());
		userWS.setPhone(user.getPhone());
		user.getRoles().forEach(role -> {
			userWS.getRoles().add(role.getName());
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
		return (user.getEnabled().equals(Boolean.TRUE) && user.getDeleted().equals(Boolean.FALSE)) ? true : false;

	}

	@Override
	public void validateUserWS(UserWS userWS) {
		Preconditions.check(StringUtils.isEmpty(userWS.getEmail()), ExceptionConstants.EMAIL_CANNOT_BE_EMPTY);
		Preconditions.check(StringUtils.isEmpty(userWS.getFname()), ExceptionConstants.FIRST_NAME_CANNOT_BE_EMPTY);
		Preconditions.check(StringUtils.isEmpty(userWS.getLname()), ExceptionConstants.LAST_NAME_CANNOT_BE_EMPTY);
		Preconditions.check(StringUtils.isEmpty(String.valueOf(userWS.getPhone())), ExceptionConstants.PHONE_NUMBER_CANNOT_BE_EMPTY);
	}

	@Override
	@Transactional
	public void updateUser(UserWS userWS, String userUuid) {
		User enabledUser = readUserByUuid(userUuid);
		enabledUser.setFname(userWS.getFname());
		enabledUser.setLname(userWS.getLname());
		enabledUser.setPhone(userWS.getPhone());
		userRepository.save(enabledUser);
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

	@Override
	@Transactional
	public UserWS readUser(String userUuid) {
		User user = readUserByUuid(userUuid);
		return convertEntityToWS(user);
	}

	@Override
	@Transactional
	public Boolean checkIfUserAlreadyExist(String email) {
		User user = findByEmail(email);
		return user == null ? false : true;
	}

	@Override
	@Transactional
	public User findByEmail(String email) {
		return userRepository.findByemail(email);
	}
}











