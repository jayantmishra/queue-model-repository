package com.softcell.accessControl.dataObject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name="softcell_acl_permission")
public class AccessControlPermissionData {

	public AccessControlPermissionData()
	{
		
	}
	
	@Id
	@Column (name="")
	private int acl_id;
	
	@Column (name="user_id")
	private int user_id;
	
	@Column (name="permission")
	private int permission;

	public int getAcl_id() {
		return acl_id;
	}

	public void setAcl_id(int acl_id) {
		this.acl_id = acl_id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getPermission() {
		return permission;
	}

	public void setPermission(int permission) {
		this.permission = permission;
	}
	
}

