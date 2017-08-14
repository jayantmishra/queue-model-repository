package com.softcell.routing.service;
import java.util.List;

import org.hibernate.jdbc.WorkExecutor;

import com.softcell.common.dataObject.ActivityData;
import com.softcell.common.exceptions.WorkException;
import com.softcell.routing.workflow.dataObject.Rule;
import com.softcell.routing.workflow.dataObject.WorkFlowXmlParser;
import com.softcell.services.framework.IInstance;

public class RulesInstance implements IInstance {

	private volatile boolean mStopProcessing = false;
	public RulesInstanceData mInstanceData = null;
	
	public RulesInstance(RulesInstanceData instanceData)
	{
		mInstanceData = instanceData;
		
	}
	@Override
	public void startInstance()  {
		CurrentWork currentWork = null;
		CurrentWork reassignedWork = null;
		boolean reassigned = false;
		int reassignFreq = 1;
		int count = 0;
		
		while (!mStopProcessing)
		{
			try
			{
				reassigned = false;
				count++;
				
				if (count >reassignFreq)
				{
					System.out.println("Calling getReassignedWork()");
					reassignedWork = RulesService.getReassignedWork(mInstanceData.mInstanceId);
					
					if (reassignedWork != null)
					{
						reassigned = true;
						doReassignmentWork(reassignedWork);
					}
					
					RulesService.finishReassignedWork(mInstanceData.mInstanceId);
					count = 0;
				}
				
				if (!reassigned)
				{
					System.out.println("Calling getwork()");
					currentWork = RulesService.getWork(mInstanceData.mInstanceId);
					
					if (currentWork != null)
					{
						dowork(currentWork);
					}
					
					if (!mStopProcessing)
						RulesService.finishWork(mInstanceData.mInstanceId);
				}
				
			}
			catch (Exception e)
			{
				
				try{
				if(!reassigned)
				{
					RulesService.finishWork(mInstanceData.mInstanceId);
				}
				else
				{
					RulesService.finishReassignedWork(mInstanceData.mInstanceId);
					reassigned =false;
				}
				}
				catch (Exception ex)
				{
					System.out.println("Exception here");;
				}
					
			}
			try
			{
				Thread.sleep(5*1000); // 5sec
			}
			catch (InterruptedException e)
			{
				mStopProcessing=true;
				System.out.println("workflow instance interuppted"+ mInstanceData.mInstanceId);
			}
		}
		System.out.println("workflow instance outside while loop");
	}

	private void dowork(CurrentWork currentWork) {
		
	Rule rule =	createWorkflowDataObjectsFromXML();
	
	List activityList = currentWork.getListActivityObjects();
	ActivityData activityData = null;
	
	for(int i=0; i < activityList.size(); i++)
	{
		activityData = (ActivityData) activityList.get(i);
		RuleMeaning ruleMeaning = new RuleMeaning();
		
		if(ruleMeaning.initialize(activityData))
		{
			int queue_id = ruleMeaning.applyWorkflow(rule);
			ruleMeaning.mQueueId = queue_id;
			
			
			ruleMeaning.saveInDb();
		}
	}
	
	}
	private Rule createWorkflowDataObjectsFromXML() {
		
		return WorkFlowXmlParser.parseWorkflowXml();		
	}
	private void doReassignmentWork(CurrentWork reassignedWork) {
		
		System.out.println("doing reassigned work : rules instance");
		
		List actvityList = reassignedWork.getListActivityObjects();
		int size = actvityList.size();
		ActivityData activityData  =null;
		for (int i=0 ; i< size; i++)
		{
			activityData =(ActivityData) actvityList.get(i);
			int queueId = activityData.getQueue_id();
			
			
			int deptId = activityData.getDepartment_id();
			RuleMeaning ruleMeaning = new RuleMeaning();
			if (ruleMeaning.initializeforAssignment(activityData))
			{
				ruleMeaning.routeActivity(queueId, deptId);
				ruleMeaning.saveInDb();
				System.out.println("activity: "+activityData.getActivity_id()+" assigned to agent : "+ruleMeaning.mAssignedTo);
			}			
		}
	}
	@Override
	public void stopInstance() {
	
		mStopProcessing = true;
	}

}
