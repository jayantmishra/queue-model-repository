package com.softcell.accessControl.dataObject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="softcell_action")
public class ActionData {
	
	public ActionData()
	{
		
		
		
	}
	
	@Column(name="action_id")
	private int action_id;
	
	@Column (name="action_name")
	private String action_name;
	
	@Column (name="action_desc")
	private String action_desc;
	
	@Column (name="action_type")
	private String action_type ;
	
	@Column (name="bit_number")
	private int bit_number;

	public int getAction_id() {
		return action_id;
	}

	public void setAction_id(int action_id) {
		this.action_id = action_id;
	}

	public String getAction_name() {
		return action_name;
	}

	public void setAction_name(String action_name) {
		this.action_name = action_name;
	}

	public String getAction_desc() {
		return action_desc;
	}

	public void setAction_desc(String action_desc) {
		this.action_desc = action_desc;
	}

	public String getAction_type() {
		return action_type;
	}

	public void setAction_type(String action_type) {
		this.action_type = action_type;
	}

	public int getBit_number() {
		return bit_number;
	}

	public void setBit_number(int bit_number) {
		this.bit_number = bit_number;
	}
	
	
}
