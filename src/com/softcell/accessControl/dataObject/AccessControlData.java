package com.softcell.accessControl.dataObject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="softcell_acl")
public class AccessControlData {

	public AccessControlData()
	{
		
	}
	
	@Id
	@Column(name="acl_id")
	private int acl_id;
	
	@Column(name="resource_id")
	private int resource_id;
	
	@Column(name="resource_type")
	private String resource_type;
	
	@Column(name="department_id")
	private int department_id;

	public int getAcl_id() {
		return acl_id;
	}

	public void setAcl_id(int acl_id) {
		this.acl_id = acl_id;
	}

	public int getResource_id() {
		return resource_id;
	}

	public void setResource_id(int resource_id) {
		this.resource_id = resource_id;
	}

	public String getResource_type() {
		return resource_type;
	}

	public void setResource_type(String resource_type) {
		this.resource_type = resource_type;
	}

	public int getDepartment_id() {
		return department_id;
	}

	public void setDepartment_id(int department_id) {
		this.department_id = department_id;
	}
	
	
}
