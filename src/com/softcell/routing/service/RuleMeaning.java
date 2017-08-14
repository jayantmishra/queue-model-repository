package com.softcell.routing.service;

import java.util.Iterator;
import java.util.Set;

import org.hibernate.Session;

import com.softcell.common.dao.ActivityDao;
import com.softcell.common.dao.QueueDao;
import com.softcell.common.dataObject.ActivityData;
import com.softcell.hibernate.SessionFactoryHelper;
import com.softcell.routing.dataObject.Queue;
import com.softcell.routing.dataObject.UserLoad;
import com.softcell.routing.service.Constants.RuleConstants;
import com.softcell.routing.workflow.dataObject.Rule;

// context of rules as in initial reference of rule for each loan activity
//Tried but coudnt come up with better name :P
public class RuleMeaning {

	public ActivityData mActivityData;
	
	public Queue queueData;
	
	public int mActivityId;
	
	public int mQueueId;
	
	public int mdepartmentId;
	
	public int mActivityStatus;
	
	public int mActivitySubStatus;
	
	
	public int mUserLastWorked;
	
	public int mAssignedTo;
	
	public String mDescription;
	
	//Source to queue assignment
	public boolean initialize(ActivityData activityData)
	{
		mActivityData = activityData;
		mActivityId = activityData.getActivity_id();
		mdepartmentId = activityData.getDepartment_id();
		mAssignedTo = activityData.getAssigned_to();
		
		mUserLastWorked=activityData.getUser_last_worked();
		mDescription = activityData.getDescription();
		
		mActivityStatus = RuleConstants.queue_assignment_status;
		mActivitySubStatus =  RuleConstants.queue_assignment_sub_status;
		
		return true;		
	}
	

	//Queue to user assignment
	public boolean initializeforAssignment(ActivityData activityData)
	{
		
		mActivityData = activityData;
		mActivityId = activityData.getActivity_id();
		mdepartmentId = activityData.getDepartment_id();
		mAssignedTo = -1;
		mUserLastWorked = activityData.getUser_last_worked();
		mDescription = activityData.getDescription();
		
		
		ActivityDao.workfglowInProgress(activityData);
		
		return true;
	}


	//Routing activity from queue to user
	public void routeActivity(int destQueueId, int deptId) {
		// TODO Auto-generated method stub
		Queue queueData = QueueDao.getQueueData(destQueueId);
		int agent_id = -1;
		if(queueData.getQueue_state() == Queue.STATE_ACTIVE)
		{
			
			if (queueData.getQueue_routing_type() == RuleConstants.ROUTE_LOAD_BALANCE)
			{
				agent_id = QueueLoader.queueLoadBalancer(destQueueId, mActivityId, deptId);
				if(agent_id == -1)
				{
					System.out.println("all users maxed out in terms of load for this department: "+deptId);
				}
				else
				{
					try{
					increaseUserLoad(agent_id);
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}
				}
			}
		}
		mAssignedTo  = agent_id;
		mQueueId = -1;
		mActivityStatus = RuleConstants.agent_assignment_status;
		mActivitySubStatus = RuleConstants.agent_assignment_sub_status;
		mdepartmentId = deptId;	
		
	}



	private void increaseUserLoad(int agent_id) {
		
		Session session = SessionFactoryHelper.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		UserLoad ul = session.get(UserLoad.class, agent_id);
		ul.setLoad_count(ul.getLoad_count()+ 1);		
		session.getTransaction().commit();		
	}


	public void saveInDb() {
		Session session = SessionFactoryHelper.getSessionFactory().getCurrentSession();
		
		session.beginTransaction();
		
		ActivityData actData = session.get(ActivityData.class, mActivityId);
		actData.setActivity_sub_status(mActivitySubStatus);
		actData.setDepartment_id(mdepartmentId);
		actData.setLocked("n");
		actData.setAssigned_to(mAssignedTo);
		actData.setQueue_id(mQueueId);
		actData.setUser_last_worked(mUserLastWorked);
		actData.setActivity_status(mActivityStatus);
		actData.setActivity_sub_status(mActivitySubStatus);
		
		System.out.println("persisted in db");
		session.getTransaction().commit();
		
	}


	/*
	 * Returns the final queue id after the rule has been applied
	 */
	public int applyWorkflow(Rule rule) {
	
		Set<Integer> keys = rule.getoWorkflow().keySet();
		
		Iterator itr = keys.iterator();
		
		while(itr.hasNext())
		{
			int id = (int) itr.next();
			System.out.println("applying workflow id:"+id+": name "+rule.getoWorkflow().get(id).getName());
			
			int startItem = rule.getoWorkflow().get(id).getStartItemId();
			
			Set<Integer> nodekeys = rule.getoNode().keySet();
			Iterator nodekeyiterator = nodekeys.iterator();
			while(nodekeyiterator.hasNext())
			{
				int nodeId = (int) nodekeyiterator.next();
				if (nodeId == startItem)
				{
					int ruleSetId =  rule.getoNode().get(nodeId).getRuleSetId();
					Set<Integer> ruleSetKeys = rule.getoRuleSet().keySet();
					Iterator rulesetiterator = ruleSetKeys.iterator();
					
					while(rulesetiterator.hasNext())
					{
						int ruleId = (int) rulesetiterator.next();
						
						if(ruleSetId == ruleId)
						{
							int finalQueue = rule.getoRuleSet().get(ruleId).getActionData().getValue();
							return finalQueue;
						}
					}
				}					
			}
					
		}
		return -1;
	}
}
