package com.softcell.routing.service;

import java.util.Hashtable;
import java.util.Map;
import com.softcell.common.exceptions.MethodExecutionException;
import com.softcell.routing.service.Constants.RuleConstants;
import com.softcell.services.dataObject.InstanceData;
import com.softcell.services.dataObject.ProcessData;
import com.softcell.services.framework.FrameWorkConstants;
import com.softcell.services.framework.IService;

public class RulesService implements IService{

	private static ThreadPool mThreadPool = null;
	private static Hashtable instanceTable = new Hashtable();
	private static WorkAllocation sWorkAllocation = null;

	@Override
	public void init(Map initData) throws MethodExecutionException {
		
		
	}

	@Override
	public void startProcess(Map serviceInitParams) throws MethodExecutionException {
	
		ProcessData  processData = (ProcessData) serviceInitParams.get(FrameWorkConstants.PROCESS_DATA);
		mThreadPool = new ThreadPool(0, processData.getMax_instances() + 1, 1);

	}

	@Override
	public void startInstance(Map instanceInitParams) throws MethodExecutionException {
		
		InstanceData instanceData = (InstanceData) instanceInitParams.get(FrameWorkConstants.INSTANCE_DATA);
		
		int instanceId = instanceData.getInstance_id();
		
		if(!instanceTable.containsKey(Long.valueOf(instanceId)))
		{
			instanceTable.put(instanceId, instanceId);			
		}
		
		if (sWorkAllocation == null)
		{
			sWorkAllocation = new WorkAllocation(instanceId, RuleConstants.BATCH_SIZE);
			mThreadPool.startInstance(0, sWorkAllocation);
			
			System.out.println("Started Work allocation Thread");
		}
		
		RulesInstanceData rulesInstanceData = new RulesInstanceData(instanceId);
		try
		{
			mThreadPool.startInstance(instanceId, new RulesInstance(rulesInstanceData));
		}
		catch (Exception e)
		{
			throw new MethodExecutionException(e.getMessage());
		}
		
		
	}

	@Override
	public void stopProcess() throws MethodExecutionException {
		
		
	}

	@Override
	public void stopInstance(long instanceId) throws MethodExecutionException {
		
		
	}

	public static CurrentWork getReassignedWork(int mInstanceId) {
		// TODO Auto-generated method stub
		return sWorkAllocation.getReassignedWork(mInstanceId);
	}

	public static void finishReassignedWork(int mInstanceId) {
		// TODO Auto-generated method stub
		if (sWorkAllocation != null)
			sWorkAllocation.finishReassignedWork(mInstanceId);
		
		
	}

	public static CurrentWork getWork(int mInstanceId) {
		// TODO Auto-generated method stub
		return (sWorkAllocation.getWork(mInstanceId));
	}

	public static void finishWork(int mInstanceId) {
		// TODO Auto-generated method stub
		if (sWorkAllocation != null)
			sWorkAllocation.finishWork(mInstanceId);
	
	}

}
