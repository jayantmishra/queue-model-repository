package com.softcell.routing.dataObject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="softcell_queue")
public class Queue {

	public static final int STATE_INACTIVE = 0;
	public static final int STATE_ACTIVE = 1;
	
	public Queue(){}
	
	@Id
	@Column(name="queue_id")
	public int queue_id;
	
	@Column(name="queue_name")
	public String queue_name;
	
	@Column (name="queue_state")
	public int queue_state;
	
	@Column (name="department_id")
	public int department_id;
	
	@Column (name="queue_description")
	public String queue_description;
	
	@Column (name="queue_routing_type")
	public int queue_routing_type;

	public int getQueue_id() {
		return queue_id;
	}

	public void setQueue_id(int queue_id) {
		this.queue_id = queue_id;
	}

	public String getQueue_name() {
		return queue_name;
	}

	public void setQueue_name(String queue_name) {
		this.queue_name = queue_name;
	}

	public int getQueue_state() {
		return queue_state;
	}

	public void setQueue_state(int queue_state) {
		this.queue_state = queue_state;
	}

	public int getDepartment_id() {
		return department_id;
	}

	public void setDepartment_id(int department_id) {
		this.department_id = department_id;
	}

	public String getQueue_description() {
		return queue_description;
	}

	public void setQueue_description(String queue_description) {
		this.queue_description = queue_description;
	}

	public int getQueue_routing_type() {
		return queue_routing_type;
	}

	public void setQueue_routing_type(int queue_routing_type) {
		this.queue_routing_type = queue_routing_type;
	}
	
	
}
