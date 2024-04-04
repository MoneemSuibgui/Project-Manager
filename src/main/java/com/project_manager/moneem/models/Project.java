package com.project_manager.moneem.models;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "projects")
public class Project {

	// member variables

	// @GeneratedValue with strategy=GenerationType.IDENTITY is a Hibernate
	// annotation that generates the value for the column of a database table. In
	// this case, the value is set by the table itself and should be unique
	@Id
	// The @GeneratedValue annotation provides the specification of generation
	// strategies for the primary keys values
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty(message = "* Title must be provided")
	private String title;

	@NotEmpty(message = "* Description is required")
	@Size(min = 3, message = "* Description must at least 3 characters")
	private String description;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	// this annotation doesn't accepte date in the past for this field when we
	// create user object
	@FutureOrPresent(message = "* Due date must not be in the past")
	private Date dueDate;

	@Column(updatable = false)
	// @Column(updatable = false) this annotation for when we update user we didn't
	// change/set the value of this field
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createdAt;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date updatedAt;

	// n:1 relationship between user and project : one project created by one user &  user can create many projects
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User creator;
	
	
	// n:n relationship : many projects can joined by many users & many users can joined many projects
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(
			name="teams_projects",
			joinColumns=@JoinColumn(name="project_id"),
			inverseJoinColumns=@JoinColumn(name="user_id")
			)
	List<User> usersTeam;
	
	
	// 1:n relationship : one project can have many projects
	@OneToMany(mappedBy="project",fetch=FetchType.LAZY)
	private List<Task> tasks;
	

	// beans constructor
	public Project() {
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
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

	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}

	public List<User> getUsersTeam() {
		return usersTeam;
	}

	public void setUsersTeam(List<User> usersTeam) {
		this.usersTeam = usersTeam;
	}

	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}
}
