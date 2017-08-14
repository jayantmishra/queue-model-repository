package com.softcell.common.dataObject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="softcell_activity")
public class ActivityData {
	
	public ActivityData()
	{
		
	}
	
	public int getActivity_id() {
		return activity_id;
	}

	public void setActivity_id(int activity_id) {
		this.activity_id = activity_id;
	}

	public int getDepartment_id() {
		return department_id;
	}

	public void setDepartment_id(int department_id) {
		this.department_id = department_id;
	}

	public int getActivity_status() {
		return activity_status;
	}

	public void setActivity_status(int activity_status) {
		this.activity_status = activity_status;
	}

	public int getActivity_sub_status() {
		return activity_sub_status;
	}

	public void setActivity_sub_status(int activity_sub_status) {
		this.activity_sub_status = activity_sub_status;
	}

	public int getAssigned_to() {
		return assigned_to;
	}

	public void setAssigned_to(int assigned_to) {
		this.assigned_to = assigned_to;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getQueue_id() {
		return queue_id;
	}

	public void setQueue_id(int queue_id) {
		this.queue_id = queue_id;
	}

	public int getUser_last_worked() {
		return user_last_worked;
	}

	public void setUser_last_worked(int user_last_worked) {
		this.user_last_worked = user_last_worked;
	}

	public String getLocked() {
		return locked;
	}

	public void setLocked(String locked) {
		this.locked = locked;
	}

	@Id
	@Column(name="activity_id")
	public int activity_id;
	
	@Column(name="department_id")
	public int department_id;
	
	@Column(name="activity_status")
	public int activity_status;
	
	@Column(name="activity_sub_status")
	public int activity_sub_status;
	
	@Column(name="assigned_to")
	public int assigned_to;
	
	@Column(name="description")
	public String description;
	
	@Column(name="queue_id")
	public int queue_id;
	
	@Column(name="user_last_worked")
	public int user_last_worked;
	
	@Column(name="locked")
	public String locked;
	
	

}
