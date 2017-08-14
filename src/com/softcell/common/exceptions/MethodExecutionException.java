package com.softcell.common.exceptions;

public class MethodExecutionException extends SoftCellException{

	public MethodExecutionException(String reason)
	{
		super(reason);
	}

	public MethodExecutionException(Exception e)
	{
		super(e);
	}

	public MethodExecutionException(String reason, Exception e)
	{
		super(reason, e);
	}
	
	
}
