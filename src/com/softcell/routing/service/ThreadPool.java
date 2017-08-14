package com.softcell.routing.service;

import java.util.Vector;

import com.softcell.common.exceptions.MethodExecutionException;
import com.softcell.services.framework.IInstance;


public class ThreadPool {

	private int mMaxThreads = 0;
	private int mMinThreads = 0;
	
	private int mMaxIdleTime = -1;
	
	private Vector mAvaiableThreads = new Vector();
	
	private class PoolThread extends Thread
	{
		// idle thread.. can be used
		private boolean mIdle;
		
		private int mInstanceId = -1;
		
		private IInstance mCurrentInstance = null;
		
		public PoolThread()
		{
			
		}
		
		public PoolThread(String threadName)
		{
			super(threadName);
		}
		
		public void run()
		{
			while(true)
			{
				while(true)
				{
					// no point 
					if(mCurrentInstance == null)
					{
						System.out.println("Idle thread : "+mInstanceId);
						mIdle = true;
						break;
					}
					try
					{
						mCurrentInstance.startInstance();
					}
					catch (Exception e)
					{
						System.out.println("failed to start");
					}
					catch (Throwable e)
					{
						System.out.println("failed to start");
					}
					System.out.println("instance started");
					
					
				}
				try
				{
					synchronized (this) {
						wait(mMaxIdleTime);
					}
				}
				catch (InterruptedException e)
				{
					System.out.println("interrupted..... ");
					return;
				}
			}
		}
	}
	
	public ThreadPool(int minThread, int maxThread, int sec)
	{
		mMaxIdleTime = sec *1000;
		mMinThreads = minThread;
		mMaxThreads = maxThread;
	}
	
	synchronized public void startInstance(int instanceId, IInstance instance) throws MethodExecutionException
	{
		System.out.println("inside thread pool start instance");
		
		//Find idle thread if any for this instance
		PoolThread p1 = findThreadForInstance(instanceId);
		if(p1 != null)
		{
			System.out.println("thread already runing for this instance");
			throw new MethodExecutionException("thread already runing for this instance");
		}
		
		PoolThread p = findIdleThread();
		
		
		if(p == null)
		{
			if(mAvaiableThreads.size() < mMaxThreads)
			{
				System.out.println("creating new thread........");
				
				p = new PoolThread(getThreadName(instanceId));
				p.mIdle = false;
				p.mInstanceId = instanceId;
				p.mCurrentInstance = instance;
				p.start();
				
				 mAvaiableThreads.add(p);
				 return;
			}
			else
			{
				//Restricting creation of more threads 
				throw new MethodExecutionException("max threads count already reached");
			}
		}
		else
		{
			System.out.println("using an already existing thread");
			
			p.mCurrentInstance = instance;
			p.mIdle= false;
			p.mInstanceId = instanceId;
			
			
		}
	}

	private PoolThread findIdleThread() {
		
		PoolThread p = null;
		for (int i=0; i< mAvaiableThreads.size(); i++)
		{
			p = (PoolThread) mAvaiableThreads.get(i);
			if(p.mIdle)
				return p;
		}
		return null;
	}

	private String getThreadName(int instanceId) {
		
		return "thread:"+instanceId+ " :";
	}

	private PoolThread findThreadForInstance(int instanceId) {
		
		PoolThread p = null;
		for (int i =0 ; i< mAvaiableThreads.size(); i++)
		{
			p = (PoolThread) mAvaiableThreads.get(i);
			if(p.mInstanceId == instanceId && !p.mIdle)
				return p;
		}
		return null;
	}
	
}
