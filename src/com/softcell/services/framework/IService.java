package com.softcell.services.framework;

import java.util.Map;

import com.softcell.common.exceptions.MethodExecutionException;

public interface IService {
	
	public void init(Map initData) throws MethodExecutionException;
	
	public void startProcess(Map serviceInitParams)throws MethodExecutionException;
	
	public void startInstance(Map instanceInitParams)throws MethodExecutionException;
	
	public void stopProcess()throws MethodExecutionException;
	
	public void stopInstance(long instanceId)throws MethodExecutionException;
		

}
