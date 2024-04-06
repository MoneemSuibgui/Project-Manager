package com.project_manager.moneem.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project_manager.moneem.models.Project;
import com.project_manager.moneem.models.Task;
import com.project_manager.moneem.models.User;
import com.project_manager.moneem.repositories.TaskRepository;

@Service
public class TaskService {

	@Autowired 
	private TaskRepository repository;
	
	// create task
	public Task add(Task task,User creatorTask,Project project) {
		Task newTask=new Task(task.getTicket(),project,creatorTask);
		return this.repository.save(newTask);
	}
	
	// get all tasks for specific project
	public List<Task> getAllByProject(Project project){
		return this.repository.findAllByProject(project);
	}
}
