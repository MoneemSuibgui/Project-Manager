package com.project_manager.moneem.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project_manager.moneem.models.Project;
import com.project_manager.moneem.models.User;

@Repository
public interface ProjectRepository extends JpaRepository<Project,Long>{

	// get all projects
	List<Project> findAll();
	
	// get all projects for user
	List<Project> findAllByCreator(User user);
	
	// get all projects doesn't contains the user projects
	List<Project> findAllByCreatorNot(User user);
	
	
	
}
