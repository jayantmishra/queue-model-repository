package com.softcell.hibernate;

import java.util.Set;

import javax.persistence.Entity;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.reflections.Reflections;

import com.softcell.common.dataObject.DepartmentData;
import com.softcell.routing.dataObject.Queue;
import com.softcell.routing.workflow.dataObject.ActionData;
import com.softcell.routing.workflow.dataObject.NodeData;
import com.softcell.services.dataObject.InstanceData;

public class SessionFactoryHelper {
	private static final SessionFactory sessionFactory;

	static {
		try {
			final Configuration configuration = new Configuration();

			Reflections reflections = new Reflections(DepartmentData.class.getPackage().getName());
			Set<Class<?>> classes = reflections.getTypesAnnotatedWith(Entity.class);

			for (final Class<?> clazz : classes) {
				configuration.addAnnotatedClass(clazz);
			}
			reflections = new Reflections(Queue.class.getPackage().getName());
			classes = reflections.getTypesAnnotatedWith(Entity.class);

			for (final Class<?> clazz : classes) {
				configuration.addAnnotatedClass(clazz);
			}

			reflections = new Reflections(InstanceData.class.getPackage().getName());
			classes = reflections.getTypesAnnotatedWith(Entity.class);
			for (final Class<?> clazz : classes) {
				configuration.addAnnotatedClass(clazz);
			}
			
			reflections = new Reflections(ActionData.class.getPackage().getName());
			classes = reflections.getTypesAnnotatedWith(Entity.class);
			for (final Class<?> clazz : classes) {
				configuration.addAnnotatedClass(clazz);
			}
			
			reflections = new Reflections(NodeData.class.getPackage().getName());
			classes = reflections.getTypesAnnotatedWith(Entity.class);
			for (final Class<?> clazz : classes) {
				configuration.addAnnotatedClass(clazz);
			}
			
			sessionFactory = configuration.configure("hibernate.cfg.xml").buildSessionFactory();

		} catch (Throwable e) {
			System.out.println("Error in creating SessionFactory object." + e.getMessage());
			throw new ExceptionInInitializerError(e);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	public static void main(String[] args)
	{
		System.out.println("Testing");
	}
}