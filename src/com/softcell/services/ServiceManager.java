package com.softcell.services;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;

import com.softcell.common.exceptions.ServiceManagerException;
import com.softcell.hibernate.SessionFactoryHelper;
import com.softcell.services.dataObject.InstanceData;
import com.softcell.services.dataObject.ProcessData;
import com.softcell.services.dataObject.ServiceData;
import com.softcell.services.framework.FrameWorkConstants;

public class ServiceManager {

	private static Hashtable mInstanceProcessMap = new Hashtable();
	private static Hashtable mServiceControllerList = new Hashtable();
	public static void startProcess(int processId) throws ServiceManagerException {
		try {
			
			ProcessData processData = LaunchUtils.getProcess(processId);

			int serviceId = processData.getService_id();

			ServiceData serviceData = LaunchUtils.getService(serviceId);

			String processName = processData.getProcess_name();

			ServiceController serviceController = new ServiceController();

			serviceController.setProcessState(processId, FrameWorkConstants.STATE_STARTING);

			Map init = prepareInit(processData, serviceData);
			serviceController.init(init);
			serviceController.startProcess(init);

			serviceController.setProcessState(processId, FrameWorkConstants.STATE_RUNNING);
			mServiceControllerList.put(processId, serviceController);
			
		} catch (Exception e) {
			System.out.println("ServiceManager :: startProcess exception" + e);
			throw new ServiceManagerException("ServiceManagerException", e);
		}

	}

	public static void startInstance(int instanceId) throws ServiceManagerException {
		System.out.println("Service manager : startInstance()");

		boolean exceptionThrown = false;
		boolean allCorrect = false;
		
		InstanceData instanceData = LaunchUtils.getInstance(instanceId);

		if (instanceData.getState() == FrameWorkConstants.STATE_STARTING)
			throw new ServiceManagerException("already starting");

		if (instanceData.getState() == FrameWorkConstants.STATE_RUNNING)			
		{
			long prcessId = getProcessForInstance(instanceId);
			if (prcessId != -1)
				throw new ServiceManagerException("instance already running");
		}

		instanceData.setState(FrameWorkConstants.STATE_STARTING);
		try{
		updateInstanceState(instanceData);
		
		}
		catch(Exception e)
		{
			System.out.println("exception while setting instance state");
			throw new ServiceManagerException(e);
		}
		
		int instanceState = FrameWorkConstants.STATE_STOPPED;
		try 
		{
			int serviceId = instanceData.getService_id();
			
			int maxInstanceCount = LaunchUtils.getMaxInstanceCountForService(serviceId);
			long runningInstanceCount = LaunchUtils.getRunningInstanceCountForService(serviceId);
			
			if (runningInstanceCount >= maxInstanceCount)
			{
				exceptionThrown = true;
				
				throw new ServiceManagerException("max instance reached");
				
			}
			int processId = findProcessForInstance(serviceId);
			System.out.println("fund process : "+processId + " for service : "+serviceId);
			try
			{
				ServiceController serviceController = (ServiceController) mServiceControllerList.get(processId);
				Map initContext = new HashMap();
				initContext.put(FrameWorkConstants.INSTANCE_DATA, instanceData);
				serviceController.starInstance(initContext);
			}
			catch (Exception e)
			{
				
				instanceState = FrameWorkConstants.STATE_ERROR;
				exceptionThrown = true;
				throw new ServiceManagerException(e);
			}
			mInstanceProcessMap.put(instanceId, processId);
			allCorrect =true;
		}
		finally 
		{
			if(!allCorrect)
			{
				instanceData.setState(instanceState);
				updateInstanceState(instanceData);
				if (!exceptionThrown)
					throw new ServiceManagerException("error starting instance :" +instanceId);
			}

		}
		
	}

	private static int findProcessForInstance(int serviceId) {
		
		int processId = -1;
		Session session = SessionFactoryHelper.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Query query = session.createQuery("Select pd.process_id from ProcessData pd "
				+ "where pd.service_id=:service_id")
				.setInteger("service_id", serviceId);
		
		processId = (int) query.uniqueResult();
		session.getTransaction().commit();
		return processId;
	}

	private static void updateInstanceState(InstanceData instanceData) {
		Session session = SessionFactoryHelper.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		InstanceData tempInstance =  session.get(InstanceData.class	, instanceData.getInstance_id());
		tempInstance.setState(instanceData.getState());
		session.getTransaction().commit();
		
	}

	private static long getProcessForInstance(long instanceId) {
		long processId = -1;
		Long processIdObj = (Long) mInstanceProcessMap.get(Long.valueOf(instanceId));
		if (processIdObj != null)
			processId = processIdObj.longValue();
		return processId;
	}

	public static Map prepareInit(ProcessData processData, ServiceData serviceData) {
		Map initContext = new HashMap();

		initContext.put(FrameWorkConstants.SERVICE_DATA, serviceData);
		initContext.put(FrameWorkConstants.PROCESS_DATA, processData);

		return initContext;
	}
}
