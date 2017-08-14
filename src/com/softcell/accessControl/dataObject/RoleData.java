package com.softcell.accessControl.dataObject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name="softcell_role")
public class RoleData {

	public RoleData() {
		// TODO Auto-generated constructor stub
	}
	
	@Id
	@Column (name="role_id")
	private int role_id;
	
	@Column (name="role_name")
	private String role_name;
	
	@Column (name="role_description")
	private String role_description;
	
	@Column (name="department_id")
	private int department_id;

	public int getRole_id() {
		return role_id;
	}

	public void setRole_id(int role_id) {
		this.role_id = role_id;
	}

	public String getRole_name() {
		return role_name;
	}

	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}

	public String getRole_description() {
		return role_description;
	}

	public void setRole_description(String role_description) {
		this.role_description = role_description;
	}

	public int getDepartment_id() {
		return department_id;
	}

	public void setDepartment_id(int department_id) {
		this.department_id = department_id;
	}
	
	
	
	
}
