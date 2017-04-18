package com.jft.market.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jft.market.model.Category;


public interface CategoryRepository extends JpaRepository<Category, Long> {

	public Category findByUuid(String uuid);

	public Category findByname(String name);

	public List<Category> findByNameIn(Collection<String> nameList);

	public List<Category> findAllByDeleted(Boolean isDeleted);

	public List<Category> findBycategory(Category category);
}
