package com.softcell.common.dataObject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="softcell_department")
public class DepartmentData {
	
	public DepartmentData()
	{
		
	}
	@Id
	@Column (name="department_id")
	public int department_id;
	
	@Column (name="department_name")
	public String department_name;
	
	@Column (name="department_desc")
	public String department_desc;

	public int getDepartment_id() {
		return department_id;
	}

	public void setDepartment_id(int department_id) {
		this.department_id = department_id;
	}

	public String getDepartment_name() {
		return department_name;
	}

	public void setDepartment_name(String department_name) {
		this.department_name = department_name;
	}

	public String getDepartment_desc() {
		return department_desc;
	}

	public void setDepartment_desc(String department_desc) {
		this.department_desc = department_desc;
	}
	
	

}
