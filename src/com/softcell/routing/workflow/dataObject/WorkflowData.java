package com.softcell.routing.workflow.dataObject;

public class WorkflowData {
	
	public int Id;
	
	public String name;
	
	public String desription;
	
	public int StartItemId;

	public int getStartItemId() {
		return StartItemId;
	}

	public void setStartItemId(int startItemId) {
		StartItemId = startItemId;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesription() {
		return desription;
	}

	public void setDesription(String desription) {
		this.desription = desription;
	}
	
	

}
