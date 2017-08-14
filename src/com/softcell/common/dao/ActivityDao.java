package com.softcell.common.dao;

import org.hibernate.Session;

import com.softcell.common.dataObject.ActivityData;
import com.softcell.hibernate.SessionFactoryHelper;
import com.softcell.routing.service.Constants.RuleConstants;

public class ActivityDao {

	public static void workfglowInProgress(ActivityData actData)
	{
		Session session = SessionFactoryHelper.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		int activityId =  actData.getActivity_id();
		ActivityData activityData = session.get(ActivityData.class, activityId);
		
		activityData.setActivity_status(RuleConstants.workflow_status);
		activityData.setActivity_sub_status(RuleConstants.workflow_sub_status);
		
		System.out.println("modifying actdata for workflow in prgoress");
		session.getTransaction().commit();
		
	}
}
