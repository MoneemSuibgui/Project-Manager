package com.project_manager.moneem.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project_manager.moneem.models.Project;
import com.project_manager.moneem.models.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task,Long>{

	// find all tasks
	List<Task> findAll();
	
	// find all tasks by project
	List<Task> findAllByProject(Project project);
}
