package com.example.ECommerce.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ECommerce.Enums.UserRole;
import com.example.ECommerce.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	Optional<User> findFirstByEmail(String email);

	User findByRole(UserRole userRole);
	
}
