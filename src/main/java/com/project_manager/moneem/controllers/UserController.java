package com.project_manager.moneem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.project_manager.moneem.models.LoggedUser;
import com.project_manager.moneem.models.User;
import com.project_manager.moneem.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class UserController {

	// inject UserService to the controller
	@Autowired
	private UserService userService;

	// Display route :render login register page
	@GetMapping("/")
	public String loginRegister(Model model) {
		// passing new user & LoggedUser empty objects to loginRegister form
		model.addAttribute("user", new User());
		model.addAttribute("loggedUser", new LoggedUser());
		return "loginRegister.jsp";
	}

	// Action route : register -> submit the form & add new user to the database
	@PostMapping("/register")
	public String addUser(@Valid @ModelAttribute("user") User user, BindingResult result, HttpSession session,
			Model model) {
		User newUser = this.userService.register(user, result);
		// if there is custom errors validations ,rendering the page to display errors
		// and send new empty user object
		if (result.hasErrors()) {
			model.addAttribute("loggedYser", new LoggedUser());
			return "loginRegister.jsp";
		}
		// if no erorrs validations store user id in session
		Long id = newUser.getId();
		session.setAttribute("user_id", id);
		return "redirect:/dashboard";
	}

	// Action route : login form --authenticate user
	@PostMapping("/login")
	public String login(@Valid @ModelAttribute("loggedUser") LoggedUser user, BindingResult result, Model model,
			HttpSession session) {
		this.userService.authenticate(user, result, session);
		if (result.hasErrors()) {
			// send new empty object when we rendering the page and showing errors
			model.addAttribute("user", new User());
			return "loginRegister.jsp";
		}return "redirect:/dashboard";
	}


	// logout route
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		// set the user_id stored in session to null & redirect to loginRegister route
		// ("/")
		session.setAttribute("user_id", null);
		return "redirect:/";
	}
}
