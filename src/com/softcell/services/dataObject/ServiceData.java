package com.softcell.services.dataObject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name="softcell_service")
public class ServiceData {
	public int getService_id() {
		return service_id;
	}

	public void setService_id(int service_id) {
		this.service_id = service_id;
	}

	public String getService_name() {
		return service_name;
	}

	public void setService_name(String service_name) {
		this.service_name = service_name;
	}

	public String getService_desc() {
		return service_desc;
	}

	public void setService_desc(String service_desc) {
		this.service_desc = service_desc;
	}

	public String getImplementation_class() {
		return implementation_class;
	}

	public void setImplementation_class(String implementation_class) {
		this.implementation_class = implementation_class;
	}

	public ServiceData()
	{
		
	}
	
	@Id
	@Column(name="service_id")
	private int service_id;
	
	@Column(name="service_name")
	public String service_name;
	
	@Column(name="service_desc")
	public String service_desc;
	
	@Column(name="implementation_class")
	public String implementation_class;
	

}
