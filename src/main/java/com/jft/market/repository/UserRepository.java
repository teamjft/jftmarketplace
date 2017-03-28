package com.jft.market.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.jft.market.model.User;


public interface UserRepository extends JpaRepository<User, Integer> {
}
