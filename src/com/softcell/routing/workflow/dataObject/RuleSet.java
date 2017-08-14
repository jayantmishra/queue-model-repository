package com.softcell.routing.workflow.dataObject;

public class RuleSet {

	public int ID;
	
	public ActionData actionData;
	public RuleSet()
	{
		actionData = new ActionData();
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public ActionData getActionData() {
		return actionData;
	}

	public void setActionData(ActionData actionData) {
		this.actionData = actionData;
	}
	
	
}
