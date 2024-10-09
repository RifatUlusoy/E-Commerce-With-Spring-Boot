package com.example.ECommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ECommerce.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
