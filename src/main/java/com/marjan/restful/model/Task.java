package com.marjan.restful.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name = "task")
public class Task implements java.io.Serializable {

	private static final long serialVersionUID = 4910225916550731446L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@Column(name = "user_name", length = 50)
	private String user_name;

	@Column(name = "task", length = 50)
	private String task;

	@Column(name = "status")
	private Boolean status;

	public Task() {
	}

	public Task(Long id) {
		this.id = id;
	}

	public Task(Long id, String user, String task, Boolean status) {
		this.id = id;
		this.user_name = user;
		this.task = task;
		this.status = status;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}



	public Task(String user, String task, Boolean status) {
		this.user_name = user;
		this.task = task;
		this.status = status;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getTask() {
		return this.task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public Boolean getStatus() {
		return this.status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("Id: ").append(this.id).append(", user_id: ").append(this.user_name).append(", task: ")
				.append(this.task).append(", status: ").append(this.status);
		return sb.toString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (id == null || obj == null || getClass() != obj.getClass())
			return false;
		Task toCompare = (Task) obj;
		return id.equals(toCompare.id);
	}

	@Override
	public int hashCode() {
		return id == null ? 0 : id.hashCode();
	}

}
