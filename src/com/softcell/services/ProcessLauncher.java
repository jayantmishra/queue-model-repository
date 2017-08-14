package com.softcell.services;

import java.util.ArrayList;
import java.util.List;

import com.softcell.services.dataObject.InstanceData;
import com.softcell.services.dataObject.ProcessData;
import com.softcell.services.dataObject.ServiceData;

public class ProcessLauncher {

	public static void main(String[] args) {
		
		init();

	}
	
	public static void init()
	{
		
		System.out.println("Dsm init() enter");
		
		List<ProcessData> runningServices = null;
		List<InstanceData> runningInstances =  null;
		
		
		try 
		{
			runningServices = LaunchUtils.getAllRunningProcesses();		
						
		}
		catch (Exception e)
		{
			System.out.println("exception while getting running service" + e);
		}
		
		try {
			runningInstances = LaunchUtils.getAllRunningInstances();
		}
		catch (Exception e)
		{
			System.out.println("exception while getting running instance" + e);
		}
		
		try
		{
			if ((runningInstances.size() + runningServices.size()) > 0 )
			{
				LaunchUtils.resetStates(runningServices, runningInstances);
			}
			
		}
		catch(Exception e)
		{
			System.out.println("Error while reseting instance and service");
		}
		
		
		// setting batch status for approver and queue assignment.. 
		try 
		{
			LaunchUtils.resetWATWorkingStatuses();
		}
		catch (Exception e)
		{
			System.out.println("exception while resetting WAT status");
		}
		
		//Starting all processes
		List<Integer> processList = LaunchUtils.getAllProcesses();
		long size = processList == null ? 0 : processList.size();
		
		int processId = 0;		
		for (int i=0;  i< size; i++)
		{
			try
			{
				processId =  processList.get(i);
				System.out.println("starting processID :"+processId);
				ServiceManager.startProcess(processId);
				System.out.println("started processID :"+processId);
			}
			catch (Exception e)
			{
				System.out.println("exception starting process : processId : "+processId);
			}
		}
		
		//Starting all instances
		List<Integer> instanceList = LaunchUtils.getAllInstances();
		size = instanceList == null ? 0 : instanceList.size();
		
		int instanceId = 0;
		for (int i = 0; i < size; i++)
		{
			try
			{
				instanceId = (int) instanceList.get(i);
				ServiceManager.startInstance(instanceId);
				System.out.println("Starting instanceID: "+ instanceId);
			}
			catch(Exception e)
			{
				System.out.println("exceptions starting instance : "+ instanceId);
			}
		}
		
		
	}

}
