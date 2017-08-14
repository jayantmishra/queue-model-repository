package com.softcell.services.dataObject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="softcell_instance")
public class InstanceData {
	
	public InstanceData()
	{
		
	}
	
	@Id
	@Column (name="instance_id")
	public int instance_id;
	
	@Column (name="instance_name")
	public String instance_name;
	
	@Column (name="service_id")
	public int service_id;
	
	@Column (name="state")
	public int state;

	public int getInstance_id() {
		return instance_id;
	}

	public void setInstance_id(int instance_id) {
		this.instance_id = instance_id;
	}

	public String getInstance_name() {
		return instance_name;
	}

	public void setInstance_name(String instance_name) {
		this.instance_name = instance_name;
	}

	public int getService_id() {
		return service_id;
	}

	public void setService_id(int service_id) {
		this.service_id = service_id;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}
	

}
