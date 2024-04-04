package com.project_manager.moneem.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project_manager.moneem.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	// ********* using JPQL **********

	// find all users
	@Query("SELECT u FROM User u")
	List<User> findAll();

	// find user by email
	@Query("SELECT u FROM User u WHERE u.email=?1")
	Optional<User> findByEmail(String email);


}
