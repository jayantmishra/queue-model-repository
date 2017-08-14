package com.softcell.services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder.In;

import org.hibernate.Query;
import org.hibernate.Session;

import com.softcell.common.exceptions.MethodExecutionException;
import com.softcell.common.exceptions.ServiceManagerException;
import com.softcell.hibernate.SessionFactoryHelper;
import com.softcell.services.dataObject.InstanceData;
import com.softcell.services.dataObject.ProcessData;
import com.softcell.services.dataObject.ServiceData;

public class LaunchUtils {
	
	/*
	 * INFO :
	 *  STATE :  0 -- > STOPPED
	 *  STATE :  1 ---> RUNNING 
	 */
	public static List getAllRunningProcesses() throws ServiceManagerException
	{
		System.out.println("Gettting all running services before launch");
		List<ServiceData> serviceData = null;
		try {
		Session session = SessionFactoryHelper.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		serviceData = session.createSQLQuery("Select * from softcell_process where state <> 0")
				.addEntity(ProcessData.class).list();
		session.getTransaction().commit();
		}
		catch (Exception e)
		{
			throw new ServiceManagerException(e);
		}
		return serviceData;
	}
	
	/*
	 * INFO :
	 *  STATE :  0 -- > STOPPED
	 *  STATE :  1 ---> RUNNING 
	 */
	public static List getAllRunningInstances() throws ServiceManagerException
	{
		System.out.println("Getting all running instances before launch ");
		List<InstanceData> serviceInstances = null;
		
		try{
			Session session = SessionFactoryHelper.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			serviceInstances = session.createSQLQuery("select * from softcell_instance where state <> 0")
					.addEntity(InstanceData.class).list();
			session.getTransaction().commit();
			
		}
		catch(Exception e)
		{
			throw new ServiceManagerException(e);
		}
		return serviceInstances;
	}
	
	
	public static void resetStates(List<ProcessData> processList, List<InstanceData> instanceList) throws ServiceManagerException
	{
			
		for(ProcessData p : processList)
		{
			System.out.println("Resetting state for process : " +p.process_name);
			Session session = SessionFactoryHelper.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			try
			{
				// the primary key in the table
				int processId = p.getProcess_id(); 
				ProcessData pd = session.get(ProcessData.class, processId);
				pd.setState(0);
				
				session.getTransaction().commit();
				System.out.println("process state initialized to 0");
			}
			catch (Exception e)
			{
				throw new ServiceManagerException(e);
			}
		}
		
		for(InstanceData i : instanceList)
		{
			System.out.println("Resetting state for instance "+ i.getInstance_name());
			Session session = SessionFactoryHelper.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			try
			{
				// this is the primary key in the table softcell_instance
				int instanceId = i.getInstance_id();
				InstanceData id = session.get(InstanceData.class, instanceId);
				id.setState(0);
				
				session.getTransaction().commit();
				System.out.println("instance state initialized to 0");
			}
			catch (Exception e)
			{
				throw new ServiceManagerException(e);
			}
		}
	}
	
	/*
	 * Useless now, handling this at storedProcedures
	 * TODO: remove
	 */
	public static void resetWATWorkingStatuses()
	{
		
	}
	
	/*
	 * Getting all processes that needs to be started 
	 */
	public static List<Integer> getAllProcesses()
	{
		System.out.println("Getting all processes to be started");
		List<Integer> pIds = new ArrayList<Integer>();
		Session session = SessionFactoryHelper.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		List<ProcessData> processList = session.createSQLQuery("Select * from softcell_process")
				.addEntity(ProcessData.class).list();
		
		for(ProcessData p: processList)
			pIds.add(p.getProcess_id());
		
		session.getTransaction().commit();
		return pIds;
	}

	
	public static List<Integer> getAllInstances()
	{
		System.out.println("Getting all instances");
		List<Integer> IDs = new ArrayList<>() ;
		Session session = SessionFactoryHelper.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		List<InstanceData> iList = session.createSQLQuery("select * from softcell_instance")
				.addEntity(InstanceData.class).list();
		for(InstanceData  i: iList)
			IDs.add(i.getInstance_id());
		
		session.getTransaction().commit();
		return IDs;
	}
	
	public static ProcessData getProcess(int processId)
	{
		Session session = SessionFactoryHelper.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		ProcessData pd = session.get(ProcessData.class,  processId);
		session.getTransaction().commit();
		
		return pd;
	}
	public static ServiceData getService(int serviceId)
	{
		Session session = SessionFactoryHelper.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		ServiceData sd = session.get(ServiceData.class, serviceId);
		session.getTransaction().commit();
		
		return sd;
	}
	
	public static InstanceData getInstance(int instanceId)
	{
		Session session = SessionFactoryHelper.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		InstanceData id =  session.get(InstanceData.class, instanceId);
		session.getTransaction().commit();
		return id;
	}
	
	public static int getMaxInstanceCountForService(int serviceId)
	{
		Session session = SessionFactoryHelper.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		ProcessData pd = session.get(ProcessData.class, serviceId);
		int maxCount= pd.getMax_instances();
		session.getTransaction().commit();
		return maxCount;
	}
	
	public static long getRunningInstanceCountForService(int serviceId)
	{
		try{
		Session session = SessionFactoryHelper.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		Query query = session.createQuery("select count(*) from InstanceData Id where "
				+ "Id.service_id = :service_id and Id.state = 1 ");
		query.setInteger("service_id", serviceId);
		
		Long count = (Long) query.uniqueResult();
		session.getTransaction().commit();
		
		System.out.println("instances already in running or starting state" +count);
		return count;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return -1;
	}
}
