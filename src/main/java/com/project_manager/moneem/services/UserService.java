package com.project_manager.moneem.services;

import java.util.List;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.project_manager.moneem.models.LoggedUser;
import com.project_manager.moneem.models.User;
import com.project_manager.moneem.repositories.UserRepository;

import jakarta.servlet.http.HttpSession;

@Service
public class UserService {

	// inject UserRepository 
	@Autowired
	private UserRepository repository;
	
	// register user
	public User register(User user,BindingResult result) {
		// check if the email exist in the database
		Optional<User> optionalUserByEmail=this.repository.findByEmail(user.getEmail());
		if(optionalUserByEmail.isPresent()) {
			result.rejectValue("email", "* EmailToken", "* Email already token !!");
			return null;
		}
		//check if the password and confirm password match
		if(!user.getPassword().equals(user.getConfirm())) {
			result.rejectValue("confirm","PwMatch", "* Confirm Pw and password doesn't match !!");
			return null;
		}
		// if no errors, hashing user password and save user to the db
		if(!result.hasErrors()) {
			user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
			User newUser=this.repository.save(user);
			return newUser;
		}return null;
	}
	
	// authenticate user
	public void authenticate(LoggedUser loggedUser,BindingResult result,HttpSession session) {
		Optional<User> authenticatedUser=this.repository.findByEmail(loggedUser.getEmail());
		// check the email exist in database or no 
		if(!(authenticatedUser.isPresent() && BCrypt.checkpw(loggedUser.getPassword(), authenticatedUser.get().getPassword()))) {
			// add custom error message
			result.rejectValue( "email","IvCredentials", "* Invalid Credentials !!!");
		}if(!result.hasErrors())
			//else  store the user_id in session 
			session.setAttribute("user_id", authenticatedUser.get().getId());
	}
	
	// check if user is logged in
	public boolean isConnected(HttpSession session) {
		if(session.getAttribute("user_id")!= null) {
			return true;
		}return false;
	}
	
	// get all user
	public List<User> getAll(){
		return this.repository.findAll();
	}
	
	// update user
	public User update(User user) {
		return this.repository.save(user);
	}
	

	// get user by id
	public User getById(Long id) {
		Optional<User> optionalUser=this.repository.findById(id);
		if(optionalUser.isPresent()) {
			return optionalUser.get();
		}// else
		return null;
	}
	
	
}
