package com.softcell.services.framework;

import java.util.HashMap;
import java.util.Map;

import com.softcell.services.dataObject.ServiceData;


public class ServiceFactory {
	
	protected static Map mFactoryInstance = new HashMap();
	
	public static IService createService(Map serviceInitParams)
	{
		int serviceType = FrameWorkConstants.SERVICE_TYPE;
		ServiceFactory serviceFactoryInstance = (ServiceFactory) mFactoryInstance.get(serviceType);
		
		if (serviceFactoryInstance == null)
		{
			try
			{
				String factoryClassName = FrameWorkConstants.SERVICE_FACTORY_CLASS;
				serviceFactoryInstance = (ServiceFactory) Class.forName(factoryClassName).newInstance();
				
				mFactoryInstance.put(FrameWorkConstants.SERVICE_TYPE, serviceFactoryInstance);
			}
			catch (Exception e)
			{
				System.out.println("serviceFactory :: serviceCreateFail");
			}
		}
		return serviceFactoryInstance.createProcess(serviceInitParams);
	}
	
	public IService createProcess( Map serviceInitParams)
	{
		System.out.println("SErviceFActory :: createProcess()");
		IService serviceInterface = null;
		
		ServiceData serviceData =(ServiceData) serviceInitParams.get(FrameWorkConstants.SERVICE_DATA);
		
		String serviceClassName = serviceData.getImplementation_class();
		
		try 
		{
			serviceInterface = (IService) Class.forName(serviceClassName).newInstance();
			
		}
		catch(Exception e)
		{
			System.out.println("Exception while creating process");
		}
		return serviceInterface;
		
	}

}
