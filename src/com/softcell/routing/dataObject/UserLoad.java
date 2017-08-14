package com.softcell.routing.dataObject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="softcell_user_load")
public class UserLoad {

	public UserLoad()
	{
		
	}
	@Id
	@Column(name="user_id")
	private int user_id;
	
	@Column(name="load_count")
	private int load_count;

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getLoad_count() {
		return load_count;
	}

	public void setLoad_count(int load_count) {
		this.load_count = load_count;
	}
	
	
}
