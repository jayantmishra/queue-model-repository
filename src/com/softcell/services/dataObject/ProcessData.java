package com.softcell.services.dataObject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name = "softcell_process")
public class ProcessData {
	
	public ProcessData()
	{
		
	}

	@Id
	@Column (name = "process_id")
	public int process_id;
	
	public int getProcess_id() {
		return process_id;
	}

	public void setProcess_id(int process_id) {
		this.process_id = process_id;
	}

	public String getProcess_name() {
		return process_name;
	}

	public void setProcess_name(String process_name) {
		this.process_name = process_name;
	}

	public int getService_id() {
		return service_id;
	}

	public void setService_id(int service_id) {
		this.service_id = service_id;
	}

	@Column (name = "process_name")
	public String process_name;
	
	@Column (name = "service_id")
	public int service_id;
	
	@Column (name ="state")
	public int state;
	
	@Column (name ="max_instances")
	public int max_instances;
	

	public int getMax_instances() {
		return max_instances;
	}

	public void setMax_instances(int max_instances) {
		this.max_instances = max_instances;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}
	
	
	
}
