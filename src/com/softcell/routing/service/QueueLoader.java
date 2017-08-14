package com.softcell.routing.service;

import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

import org.hibernate.Session;

import com.softcell.hibernate.SessionFactoryHelper;

public class QueueLoader {

	public static int queueLoadBalancer(int queueId,int activityId, int departmentId)
	{
		
		Session session = SessionFactoryHelper.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		StoredProcedureQuery query = session.createStoredProcedureQuery("load_balance_procedure")
				.registerStoredProcedureParameter("activity_id", Integer.class, ParameterMode.IN)
				.registerStoredProcedureParameter("department_id", Integer.class, ParameterMode.IN)
				.registerStoredProcedureParameter("queue_id", Integer.class, ParameterMode.IN)
				.registerStoredProcedureParameter("final_final_agent", Integer.class, ParameterMode.OUT)
				.setParameter("activity_id", activityId)
				.setParameter("department_id", departmentId)
				.setParameter("queue_id", queueId);
		
		query.execute();
		int finalAgent = (int) query.getOutputParameterValue("final_final_agent");
		session.getTransaction().commit();
		
		return finalAgent;
	}
	
	//Not implemented
	public static int queueRoundRobin()
	{
		return -1;
	}
	
	public static int queueCustomLoadBalancer()
	{
		return -1;
	}
}
