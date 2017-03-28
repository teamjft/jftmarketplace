package com.jft.market.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jft.market.model.Role;


public interface RoleRepository extends JpaRepository<Role, Integer> {

	public Role findByName(String roleName);
}
