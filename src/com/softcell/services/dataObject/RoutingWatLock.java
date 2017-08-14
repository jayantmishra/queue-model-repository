package com.softcell.services.dataObject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="softcell_routing_wat_lock")
public class RoutingWatLock {
	
	public RoutingWatLock()
	{
		
	}
	
	@Column (name="locked")
	private int locked;
	
	@Column (name="locked_by")
	private int locked_by;
	
	@Id
	@Column (name="lock_type")
	private int lock_type;

	public int getLocked() {
		return locked;
	}

	public void setLocked(int locked) {
		this.locked = locked;
	}

	public int getLocked_by() {
		return locked_by;
	}

	public void setLocked_by(int locked_by) {
		this.locked_by = locked_by;
	}

	public int getLock_type() {
		return lock_type;
	}

	public void setLock_type(int lock_type) {
		this.lock_type = lock_type;
	}
		

}
