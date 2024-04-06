package com.project_manager.moneem.models;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tasks")
public class Task {

	// member variables

	// @GeneratedValue with strategy=GenerationType.IDENTITY is a Hibernate
	// annotation that generates the value for the column of a database table. In
	// this case, the value is set by the table itself and should be unique
	@Id
	// The @GeneratedValue annotation provides the specification of generation
	// strategies for the primary keys values
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty(message = "Ticket must not empty")
	@Size(min = 8, message = "Ticket content must at least be 8 characters")
	private String ticket;

	@Column(updatable = false)
	// @Column(updatable = false) this annotation is for: when we update user we didn't
	// change/set the value of this field
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createdAt;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date updatedAt;

	
	//******** Connection between tasks and users(creator of the task) and the project (have many tasks)*********** 
	// n:1 relationship : many tasks can belngs to one project
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="project_id")
	private Project project;
	
	// n: 1  relationship :many tasks can created by one user
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="creator_task_id")
	private User creatorTask;
	
	
	
	// beans constructor
	public Task() {}
	
	
	// full args constructor
	public Task(String ticket,Project project, User creatorTask) {
		this.ticket = ticket;
		this.project = project;
		this.creatorTask = creatorTask;
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

	public String getTicket() {
		return ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
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



	public Project getProject() {
		return project;
	}



	public void setProject(Project project) {
		this.project = project;
	}



	public User getCreatorTask() {
		return creatorTask;
	}



	public void setCreatorTask(User creatorTask) {
		this.creatorTask = creatorTask;
	}

}
