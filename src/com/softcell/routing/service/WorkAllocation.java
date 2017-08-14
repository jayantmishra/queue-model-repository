package com.softcell.routing.service;


import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.AliasToBeanResultTransformer;
import org.hibernate.type.StandardBasicTypes;

import com.softcell.common.dataObject.ActivityData;
import com.softcell.hibernate.SessionFactoryHelper;
import com.softcell.routing.service.Constants.RuleConstants;
import com.softcell.services.dataObject.RoutingWatLock;
import com.softcell.services.framework.IInstance;

@SuppressWarnings("deprecation")
public class WorkAllocation implements IInstance {

	private boolean mStopProcessing = false;
	protected static Hashtable<Integer, WAInstanceData> mInstanceList = new Hashtable();
	
	protected Hashtable mCurrentWorkList = new Hashtable();
	private Hashtable mReassignedWorkList = new Hashtable();
	
	public WorkAllocation(int instanceId, int batchSize) {
		
		mInstanceList.put(instanceId, new WAInstanceData(batchSize));
	}
	
	private class WAInstanceData
	{	
		protected int mBatchSize;
	
		public WAInstanceData(int batchSize)
		{		
			mBatchSize = batchSize;
		}
	}
	
	@Override
	public void startInstance() {
		// TODO Auto-generated method stub
		System.out.println("workallocation startInstance");
		
		int delay = 30*1000; //30sec
		int instanceObj;
		while(!mStopProcessing)
		{
			try
			{
				for (Enumeration instanceEnum = mInstanceList.keys(); instanceEnum.hasMoreElements();)
				{
					instanceObj = (int) instanceEnum.nextElement();
					
					if (!mCurrentWorkList.containsKey(instanceObj)){
					System.out.println("current Worklist is empty of instance-->"+instanceObj);
					WAInstanceData workallocationData = (WAInstanceData) mInstanceList.get(instanceObj);
					
					ArrayList work = findWork(instanceObj, workallocationData);
					
					if (work != null && work.size() > 0)
						System.out.println("work found for instance:"+instanceObj);
						mCurrentWorkList.put(instanceObj, work);
					}
					
					if (!mReassignedWorkList.containsKey(instanceObj))
					{
						System.out.println("Reassigned worklist is empty for instance -- >"+instanceObj);
						WAInstanceData workallocationData = (WAInstanceData) mInstanceList.get(instanceObj);
						ArrayList reassignedWork = findReassignedWork(instanceObj, workallocationData);
						
						if (reassignedWork != null && reassignedWork.size() > 0)
							mReassignedWorkList.put(instanceObj, reassignedWork);						
					}
				}
				Thread.sleep(delay);
			}
			catch (InterruptedException e)
			{
				mStopProcessing = true;
				System.out.println("*****Work allocation interuptted****");
			}
			catch(Exception e)
			{
				System.out.println("**********Work allocation exception*****");
			}
		}
	}

	private ArrayList findReassignedWork(int instanceObj, WAInstanceData workallocationData) {
	
		ArrayList aList= new ArrayList();
		
		int lockType = RuleConstants.ROUTING;
		int batchSize = RuleConstants.BATCH_SIZE;
		
		
		try
		{
			if (lockWat(instanceObj, lockType, true) == 1)
			{
				
				Session session = SessionFactoryHelper.getSessionFactory().getCurrentSession();
				session.beginTransaction();
				Query query = session.createSQLQuery("CALL getAssignmentWork(:batch_size, :instance_id)")			
						
						
						.setInteger("batch_size", 2)
						.setInteger("instance_id", (int) 1);
				
				List result = query.list();
	
				if (result != null && result.size() > 0)
					aList = getCurrentReassignedWork(result);
				session.getTransaction().commit();
				
				int status = lockWat(instanceObj, lockType, false);
				if (status ==0)
					lockWat(instanceObj, lockType, false);						
			}
		}
		catch (Exception e)
		{
			aList = null;
			
		}
		return aList;
		
	}

	private ArrayList getCurrentWork(List result) {
		
		ArrayList aList = new ArrayList();
		CurrentWork currentWork = new CurrentWork();
		currentWork.setListActivityObjects(result);
		
		aList.add(currentWork);
		return aList;
	}

	private ArrayList getCurrentReassignedWork(List list) {
		System.out.println("getCurrentReassignedWork() method");
		ArrayList aList = new ArrayList();
		CurrentWork currentWork = new CurrentWork();
		List<ActivityData> result = new ArrayList<ActivityData>();
		 for(Iterator iterator = list.iterator();iterator.hasNext();){  
		
			Object[] objects = (Object[]) iterator.next();
		
			ActivityData ad = new ActivityData();
			ad.setActivity_id((int) objects[0]);
			ad.setDepartment_id((int) objects[1]);
			ad.setQueue_id((int) objects[2]);
			ad.setActivity_status((int) objects[3]);
			ad.setActivity_sub_status((int) objects[4]);
			ad.setAssigned_to((int) objects[5]);
			result.add(ad);
		}		
		currentWork.setListActivityObjects(result);
		
		aList.add(currentWork);
		return aList;
	}

	private ArrayList findWork(int instanceObj, WAInstanceData workallocationData) {
		// TODO Auto-generated method stub
		ArrayList aList = new ArrayList();
		int lockType = RuleConstants.PRE_ROUTING;
		int batchSize = workallocationData.mBatchSize;
		
		try
		{
			if(lockWat(instanceObj, lockType, true) == 1)
			{
				//Find Work StoredProcedure here
				Session session = SessionFactoryHelper.getSessionFactory().getCurrentSession();
				session.beginTransaction();
				Query query = session.createSQLQuery("CALL get_work(:instance_id, :batch_size)")
						.addEntity(ActivityData.class);
				query.setInteger("instance_id", (int) instanceObj);
				query.setInteger("batch_size", batchSize);
				
				List result = query.list();
				
				if (result != null & result.size() > 0)
				{
					aList = getCurrentWork(result);
				}
				session.getTransaction().commit();
				
				
				int status = lockWat(instanceObj, lockType, false);
				if(status == 0)
					lockWat(instanceObj, lockType, false);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			aList = null;
			
		}
		return aList;
		
	
	}
	
	public static int lockWat(int instanceId, int lockType, boolean lock)
	{
		int newValue = 0;
		int oldValue = 0;
		int retVal = 1;
		
		if (lock)
			newValue = 1;
		else
			oldValue = 1;
		
		Session session = null;
		try{
			
			session = SessionFactoryHelper.getSessionFactory().getCurrentSession();
			
			session.beginTransaction();
			RoutingWatLock dataObject = session.get(RoutingWatLock.class, lockType);
			if (lock && dataObject.getLocked() == oldValue){
				System.out.println("wat lock is unlocked so locking it");
				dataObject.setLocked(newValue);
				dataObject.setLocked_by( instanceId);
				dataObject.setLocked_by(lockType);	
			return retVal;
			}
			else if (!lock && dataObject.getLocked() == oldValue)
			{
				System.out.println("wat lock is locked unlocking it");
				dataObject.setLocked(newValue);
				dataObject.setLocked_by( instanceId);
				dataObject.setLocked_by(lockType);
				return retVal;
			}
			else
				return 0;
		}
		catch(Exception e)
		{
			System.out.println("Exception while update wat lock"+e);
			retVal = 0;
		}
		finally
		{
			session.getTransaction().commit();
		}
		return retVal;		
		
	}

	@Override
	public void stopInstance() {
		// TODO Auto-generated method stub
		mStopProcessing = true;
	}

	public CurrentWork getWork(int mInstanceId) {
		// TODO Auto-generated method stub

		System.out.println("Getting work for instance : "+mInstanceId);

		ArrayList work = (ArrayList) mCurrentWorkList.get(mInstanceId);
		
		if(work !=null)
		{
			return (CurrentWork) work.get(0);
		}
		return null;
	}
	
	public CurrentWork getReassignedWork(int instanceId)
	{
		System.out.println("Getting Reassigned work for instance : "+instanceId);

		ArrayList reassignedWrok = (ArrayList) mReassignedWorkList.get(instanceId);

		if(reassignedWrok !=null)
		{
			return (CurrentWork) reassignedWrok.get(0);
		}
		return null;
	}

	//TODO : deleting entry from assign_wat table
	public void finishReassignedWork(int mInstanceId) {
		
		System.out.println("finishing reassigned work for instance_id "+mInstanceId);
		mReassignedWorkList.remove(mInstanceId);
		
		Session session = SessionFactoryHelper.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		StoredProcedureQuery query = session.createStoredProcedureQuery("delete_assignment_work")
				.registerStoredProcedureParameter("instance_id", Integer.class, ParameterMode.IN)
				.setParameter("instance_id", (int) mInstanceId);
		
		query.execute();
		session.getTransaction().commit();
		mReassignedWorkList.remove(mInstanceId);
	}

	//TODO : deleting entry from wat table
	public void finishWork(int mInstanceId) {
		System.out.println("finishing work for instance_id");
		
		Session session = SessionFactoryHelper.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		StoredProcedureQuery query = session.createStoredProcedureQuery("deleteWork")
				.registerStoredProcedureParameter("instance_id", Integer.class, ParameterMode.IN)
				.setParameter("instance_id", (int) mInstanceId);
		
		query.execute();
		session.getTransaction().commit();
		mCurrentWorkList.remove(mInstanceId);
	}

}
