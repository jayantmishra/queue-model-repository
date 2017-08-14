package com.softcell.accessControl.dataObject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="softcell_user_role")
public class UserRoleData {

	public UserRoleData()
	{
		
	}
	
	@Column (name="user_id")
	private int user_id;
	
	@Id
	@Column(name="role_id")
	private int role_id;

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getRole_id() {
		return role_id;
	}

	public void setRole_id(int role_id) {
		this.role_id = role_id;
	}
	
	
}
