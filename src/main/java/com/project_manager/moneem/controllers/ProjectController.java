package com.project_manager.moneem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.project_manager.moneem.models.Project;
import com.project_manager.moneem.models.Task;
import com.project_manager.moneem.models.User;
import com.project_manager.moneem.services.ProjectService;
import com.project_manager.moneem.services.TaskService;
import com.project_manager.moneem.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class ProjectController {

	// inject all needed services to ProjectController using @Autowired annotation
	@Autowired
	private ProjectService projectService;

	@Autowired
	private UserService userService;

	@Autowired
	private TaskService taskService;

	
	
	
	// Display route : rendering dashoard jsp page
	@GetMapping("/dashboard")
	public String dashboard(Model model, HttpSession session) {
		// check if the user_id not in session then redirect to loginRegister jsp page
		if (userService.isConnected(session)) {
			// (user_id exist in session) get the logged in user by id
			// and send the user object to dashboard jsp file through the model object
			Long id = (Long) session.getAttribute("user_id");
			User loggedUser = this.userService.getById(id);
			model.addAttribute("loggedUser", loggedUser);
			model.addAttribute("allProjects", this.projectService.getAll());
			model.addAttribute("myProjects", this.projectService.getAllProjectsForLoggedUser(loggedUser));
			model.addAttribute("projects", this.projectService.getAllProjectsExcludeLoggedUser(loggedUser));
			return "dashboard.jsp";
		}
		return "redirect:/";

	}

	// Dislay route - rendering newProject jsp page
	@GetMapping("/projects/new")
	public String newProject(@ModelAttribute("project") Project newProject,HttpSession session) {
		if (userService.isConnected(session)) {
			return "newProject.jsp";
		}
		return "redirect:/";
	}

	// Action route submit the form & save new project to the database
	@PostMapping("/projects/new")
	public String saveProject(@Valid @ModelAttribute("project") Project project, BindingResult resultErr,
			HttpSession session) {
		// check if there is errors messgaes ,renderng the page to show errors to the
		// client
		if (resultErr.hasErrors()) {
			return "newProject.jsp";
		}
		// get the logged user (who create the project) stored in session
		Long userId = (Long) session.getAttribute("user_id");
		User loggedUser = userService.getById(userId);
		// set the creator to project and save the project
		project.setCreator(loggedUser);
		projectService.add(project);
		return "redirect:/dashboard";
	}

	// Display route ,edit prject - render editProject page
	@GetMapping("/projects/edit/{id}")
	public String editProject(@PathVariable("id") Long id, Model model,HttpSession session) {
		if (userService.isConnected(session)) {
			// get the project and send to editProject through the model object
			Project project = this.projectService.getById(id);
			model.addAttribute("editedProject", project);
			return "editProject.jsp";
		}return "redirect:/";
		
		
	}

	// join the team projects group (add project to logged user teamProjects)
	@GetMapping("/join/project/{id}")
	public String joinProject(@PathVariable("id") Long projectId, HttpSession session) {
		// get the logged in user getuser by id (cast the id stored in session)
		User user = this.userService.getById((Long) session.getAttribute("user_id"));
		// get the current project
		Project project = this.projectService.getById(projectId);
		// add the project with user to the teams projects
		user.getProjectsTeam().add(project);
		this.userService.update(user);
		return "redirect:/dashboard";
	}

	// leave the team project group(delete project from logged user teamProjects)
	@GetMapping("/leave/project/{id}")
	public String leaveTeam(@PathVariable("id") Long id, HttpSession session) {
		// get the current project & the the logged user(stored in session)
		Project currentProject = this.projectService.getById(id);
		// cast the id in session to get the id of logged user
		Long user_id = (Long) session.getAttribute("user_id");
		User loggedUser = this.userService.getById(user_id);
		// delete the project from the list of logged user team projects ,save and
		// redirect to dashboard page
		loggedUser.getProjectsTeam().remove(currentProject);
		this.userService.update(loggedUser);
		return "redirect:/dashboard";
	}

	// Action route ,update project
	@PutMapping("/projects/edit/{id}")
	public String updateProject(@Valid @ModelAttribute("editedProject") Project project, BindingResult resultErr,
			@PathVariable("id") Long id) {
		if (resultErr.hasErrors()) {
			return "editProject.jsp";
		}
		projectService.update(project);
		return "redirect:/dashboard";
	}

	// Dispaly route ,Display project details (render projectDetails.jsp page)
	@GetMapping("/projects/{id}")
	public String showProjectInfo(@PathVariable("id") Long id, Model model, HttpSession session) {
		// check if logged user id stored in session
		if(userService.isConnected(session)){
			// get the project and send it through the model object to projectDetails.jsp
			// page
			Project project = this.projectService.getById(id);
			model.addAttribute("project", project);
			Long user_id = (Long) session.getAttribute("user_id");
			User loggedUser = this.userService.getById(user_id);
			// send the logged User object through the model object
			model.addAttribute("loggedUser", loggedUser);
			return "projectDetails.jsp";
		}
		return "redirect:/";
		
	}

	// Action route : delete project
	@DeleteMapping("/projects/{id}")
	public String destroyProject(@PathVariable("id") Long id) {
		this.projectService.delete(id);
		return "redirect:/dashboard";
	}

	// Display route
	@GetMapping("/projects/{id}/tasks")
	public String seeTasks(@PathVariable("id") Long id, HttpSession session, Model model) {
		
		if(userService.isConnected(session)) {
			// passing new Task emty object into jsp form(seeTasks.jsp page) through the
			// model
			model.addAttribute("task", new Task());
			// sending the current project also to seeTasks.jsp page
			Project project = this.projectService.getById(id);
			model.addAttribute("project", project);
			model.addAttribute("tasks", this.taskService.getAllByProject(project));
			return "seeTasks.jsp";
		} return "redirect:/";
		
		
	}

	// add new task : submit the form , save the task
	@PostMapping("/projects/{id}/tasks")
	public String addTask(@Valid @ModelAttribute("task") Task task, BindingResult result, @PathVariable("id") Long id,HttpSession session) {
		if (result.hasErrors()) {
			return "seeTasks.jsp";
		}
		Project project=this.projectService.getById(id);
		Long user_id = (Long) session.getAttribute("user_id");
		User loggedUser = this.userService.getById(user_id);
		// save the task , set the creator of the task to the logged user and set the project to the task
		this.taskService.add(task,loggedUser,project);
		// redirect to the same route (seeTasks.jsp)
		return "redirect:/projects/"+id+"/tasks";
	}

}
