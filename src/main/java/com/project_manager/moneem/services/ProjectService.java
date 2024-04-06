package com.project_manager.moneem.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project_manager.moneem.models.Project;
import com.project_manager.moneem.models.User;
import com.project_manager.moneem.repositories.ProjectRepository;

@Service
public class ProjectService {

	// inject ProjectRepository to the service
	@Autowired
	private ProjectRepository repository;

	// create new project
	public Project add(Project project) {
		return this.repository.save(project);
	}

	// create new project
	public Project update(Project project) {
		return this.repository.save(project);
	}

	// get project by id
	public Project getById(Long id) {
		Optional<Project> optionalProject = this.repository.findById(id);
		if (optionalProject.isPresent()) {
			return optionalProject.get();
		} else {
			return null;
		}
	}
	
	// delete project by id
	public void delete(Long id) {
		this.repository.deleteById(id);
	}

	
	// get all projects
	public List<Project> getAll() {
		return this.repository.findAll();
	}

	// get all projects of the logged user
	public List<Project> getAllProjectsForLoggedUser(User user) {
		return this.repository.findAllByCreator(user);
	}

	// get all projects exclude loggedUser project
	public List<Project> getAllProjectsExcludeLoggedUser(User user) {
		return this.repository.findAllByCreatorNot(user);
	}

}
