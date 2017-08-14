package com.softcell.common.dao;

import org.hibernate.Session;

import com.softcell.hibernate.SessionFactoryHelper;
import com.softcell.routing.dataObject.Queue;

public class QueueDao {

	public static Queue getQueueData(int queue_id)
	{
		Session session = SessionFactoryHelper.getSessionFactory().getCurrentSession();
		
		session.beginTransaction();
		Queue queueData = session.get(Queue.class, queue_id);
		session.getTransaction().commit();
		
		return queueData;
		
	}
}
