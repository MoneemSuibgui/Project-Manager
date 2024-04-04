package com.project_manager.moneem.models;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "users")
public class User {

	// member variables

	// @GeneratedValue with strategy=GenerationType.IDENTITY is a Hibernate
	// annotation that generates the value for the column of a database table. In
	// this case, the value is set by the table itself and should be unique
	@Id
	// The @GeneratedValue annotation provides the specification of generation
	// strategies for the primary keys values
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty(message = "* First name is required")
	@Size(min = 3, max = 20, message = "* First name must between 3 and 10 characters")
	private String firstName;

	@NotEmpty(message = "* Last name is required")
	@Size(min = 3, max = 20, message = "* Last name must between 3 and 10 characters")
	private String lastName;

	@NotEmpty(message = "* Email is required")
	@Email(message = "Email is not right !!!")
	private String email;

	@NotEmpty(message = "* Password is required")
	@Size(min = 8, message = "* Password must at least 8 characters")
	private String password;

	@NotEmpty(message = "* Confirm password is required")
	@Size(min = 8, message = "* Confirm password must at least 8 characters")
	// @Transient annotation allows to didn't save this field in database when we
	// create User object
	@Transient
	private String confirm;

	@Column(updatable = false)
	// @Column(updatable = false) this annotation for when we update user we didn't
	// change/set the value of this field
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createdAt;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date updatedAt;

	// 1:n relationship between user and project : user can create many projects &  one project created by one user
	@OneToMany(mappedBy = "creator", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Project> projects;
	
	// n:n relationship one users can join many projects & one projects can joined by many users
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(
			name="teams_projects",
			joinColumns=@JoinColumn(name="user_id"),
			inverseJoinColumns=@JoinColumn(name="project_id")
			)
	List<Project> projectsTeam;
	

	//******** Connection between tasks and users(creator of the task) and the project (have many tasks)*********** 
	// 1:n relationship : one user can create many tasks
	@OneToMany(mappedBy="creatorTask",fetch=FetchType.LAZY)
	private List<Task> tasks;
	

	// beans constructor
	public User() {
	}

	// getters & setters
	@PrePersist
	public void createdOn() {
		this.createdAt = new Date();
	}

	@PreUpdate
	public void updatedOn() {
		this.updatedAt = new Date();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirm() {
		return confirm;
	}

	public void setConfirm(String confirm) {
		this.confirm = confirm;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public List<Project> getProjects() {
		return projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

	public List<Project> getProjectsTeam() {
		return projectsTeam;
	}

	public void setProjectsTeam(List<Project> projectsTeam) {
		this.projectsTeam = projectsTeam;
	}

	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}
}
