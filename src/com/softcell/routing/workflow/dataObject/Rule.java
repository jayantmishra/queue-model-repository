package com.softcell.routing.workflow.dataObject;

import java.util.Hashtable;

public class Rule {

	Hashtable<Integer, RuleSet> oRuleSet;
	
	Hashtable<Integer,WorkflowData> oWorkflow;
	
	Hashtable<Integer,NodeData> oNode;
	
	public Rule()
	{
		oRuleSet = new Hashtable<>();
		oWorkflow = new Hashtable<>();
		oNode = new Hashtable<>();
	}

	public Hashtable<Integer, RuleSet> getoRuleSet() {
		return oRuleSet;
	}

	public void setoRuleSet(Hashtable<Integer, RuleSet> oRuleSet) {
		this.oRuleSet = oRuleSet;
	}

	public Hashtable<Integer, WorkflowData> getoWorkflow() {
		return oWorkflow;
	}

	public void setoWorkflow(Hashtable<Integer, WorkflowData> oWorkflow) {
		this.oWorkflow = oWorkflow;
	}

	public Hashtable<Integer, NodeData> getoNode() {
		return oNode;
	}

	public void setoNode(Hashtable<Integer, NodeData> oNode) {
		this.oNode = oNode;
	}

	
}
