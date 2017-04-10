package com.jft.market.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.jft.market.model.User;


public interface UserRepository extends JpaRepository<User, Long> {
	

	public User findByUuid(String userUUID);

	public User findByemail(String email);
}
