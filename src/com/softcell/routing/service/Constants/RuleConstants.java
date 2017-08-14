package com.softcell.routing.service.Constants;

public class RuleConstants {

	public static final int BATCH_SIZE = 2;
	
	
	//LockTypesa
	public static final int PRE_ROUTING = 0;
	public static final int ROUTING = 1;
	
	//workflow constants
	
	public static final int workflow_status = 3000;
	public static final int workflow_sub_status = 3900;
	
	//Agent-Assignment
	
	public static final int agent_assignment_status = 5000;
	public static final int agent_assignment_sub_status = 5100;
	
	//Queeu-Assignment 
	public static final int queue_assignment_status = 4000;
	public static final int queue_assignment_sub_status = 4100;
	
	
	/*
	 * NOTE: assignment sub status would be 5200 if the 
	 * activity is assigned to agent but agent rejects it
	 * 
	 * to be used in atomic operations of apporaval and rejection
	 */
	public static final int assignment_rejection_sub_status = 5200;
	public static final int assignment_acceptance_sub_status = 5300; 
	
	//routing type
	public static final int ROUTE_LOAD_BALANCE = 1;

}
