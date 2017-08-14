package com.softcell.services;

import java.util.Map;

import org.hibernate.Session;

import com.softcell.common.exceptions.MethodExecutionException;
import com.softcell.hibernate.SessionFactoryHelper;
import com.softcell.services.dataObject.ProcessData;
import com.softcell.services.framework.FrameWorkConstants;
import com.softcell.services.framework.IService;
import com.softcell.services.framework.ServiceFactory;

public class ServiceController
{
	public IService mServiceInstance = null;
	
	public static void setProcessState(int processId, int state)
	{
		Session session = SessionFactoryHelper.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		System.out.println("setting state to :" + state+ " for process id :"+processId);
		ProcessData pd = session.get(ProcessData.class, processId);
		pd.setState(state);
		session.getTransaction().commit();
		
	}

	public void init(Map init) throws MethodExecutionException {
		
		String processName = getProcessName(init);
		System.out.println("initializing process : "+ processName);
		createServiceInstance(init);
	}
	
	private void createServiceInstance(Map init) throws MethodExecutionException
	{
		String processName = getProcessName(init);
		
		if (mServiceInstance == null)
		{
			mServiceInstance = ServiceFactory.createService(init);
			
		}
		try {
			mServiceInstance.init(init);
		} catch (Exception e) {
			throw new MethodExecutionException("Fail to create Process");
		}
	}

	public void startProcess(Map init) throws MethodExecutionException {
		
		try {
			mServiceInstance.startProcess(init);
		} catch (Exception e) {
			throw new MethodExecutionException("Failed to start process");
		}
		
	}

	private String getProcessName(Map init) {
		
		String processName = "";
		try
		{	
			ProcessData processData =  (ProcessData) init.get(FrameWorkConstants.PROCESS_DATA);
			processName = processData.getProcess_name();
		}
		catch (Exception e)
		{
			System.out.println("error while fetching procesName" + e);
		}
		return processName;
	}
	
	public void starInstance(Map initData) throws MethodExecutionException
	{
		try
		{
			if (mServiceInstance != null)
				mServiceInstance.startInstance( initData);
		}
		catch (Exception e)
		{
			throw new MethodExecutionException("faield to start instance");
		}
	}
}
