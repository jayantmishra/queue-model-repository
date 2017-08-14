package com.softcell.accessControl.dataObject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="softcell_role_action")
public class RoleActionData {

	public RoleActionData()
	{
		
	}
	
	@Column (name="role_id")
	private int role_id;
	
	@Column (name="action_id")
	private int action_id;

	public int getRole_id() {
		return role_id;
	}

	public void setRole_id(int role_id) {
		this.role_id = role_id;
	}

	public int getAction_id() {
		return action_id;
	}

	public void setAction_id(int action_id) {
		this.action_id = action_id;
	}
	
	
	
}

