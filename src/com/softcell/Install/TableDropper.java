package com.softcell.Install;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.type.StandardBasicTypes;

import com.softcell.common.dataObject.ActivityData;
import com.softcell.hibernate.SessionFactoryHelper;
import com.softcell.routing.workflow.dataObject.ActionData;

/*
 * Useless now moved logic to stored procedure 
 */
public class TableDropper {
	private static final String ALL_CSS_FILE_PATH= "C:\\Users\\magpie\\workspace\\SoftCellProject\\db\\baseDataCreationCSV";
	
	public static void main(String[] args)
	{
		Session session = SessionFactoryHelper.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		StoredProcedureQuery query = session.createStoredProcedureQuery("load_balance_procedure")
				.registerStoredProcedureParameter("activity_id", Integer.class, ParameterMode.IN)
				.registerStoredProcedureParameter("department_id", Integer.class, ParameterMode.IN)
				.registerStoredProcedureParameter("queue_id", Integer.class, ParameterMode.IN)
				.registerStoredProcedureParameter("final_final_agent", Integer.class, ParameterMode.OUT)
				.setParameter("activity_id", 100)
				.setParameter("department_id", 1)
				.setParameter("queue_id", 100);
		
		query.execute();
		int finalAgent = (int) query.getOutputParameterValue("final_final_agent");
		
		session.getTransaction().commit();
		
		
		System.out.println(finalAgent);
		System.exit(1);
	}
}
