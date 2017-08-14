package com.softcell.Install;

import java.io.File;

import org.hibernate.Query;
import org.hibernate.Session;

import com.softcell.hibernate.SessionFactoryHelper;

public class CsvLoader {

	private static final String ALL_CSS_FILE_PATH= "C:\\Users\\magpie\\workspace\\SoftCellProject\\db\\baseDataCreationCSV";
	
	public static void main(String[] args)
	{
		
		File baseFolder = new File(ALL_CSS_FILE_PATH);
		
		File[] listFiles = baseFolder.listFiles();
		Session session = SessionFactoryHelper.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		//Before loading the data truncate all tables 
		Query query1 = session.createSQLQuery("CALL truncate_all_tables()");
		query1.executeUpdate();
		
		
		for (File f : listFiles)
		{
			String tableName = f.getName().split("\\.")[0];
			StringBuilder sqlQueryBuilder = new StringBuilder();
			sqlQueryBuilder.append("LOAD DATA LOCAL INFILE :filename ");
			sqlQueryBuilder.append("INTO TABLE " + tableName+" ");
			sqlQueryBuilder.append("FIELDS TERMINATED BY ',' ");
			sqlQueryBuilder.append("ENCLOSED BY '\"' ");
			sqlQueryBuilder.append("LINES TERMINATED BY '\r\n'");
			sqlQueryBuilder.append("IGNORE 1 ROWS");
			Query query = session.createSQLQuery(sqlQueryBuilder.toString())
					.setString("filename", f.getPath());
					
		
			System.out.println("updated rows : "+query.executeUpdate());
			
		}
		session.getTransaction().commit();
		System.exit(1);
	}
}
