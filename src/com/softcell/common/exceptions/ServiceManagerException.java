package com.softcell.common.exceptions;

public class ServiceManagerException extends SoftCellException{

	public ServiceManagerException(Exception e) {
		super(e);
		// TODO Auto-generated constructor stub
	}

	public ServiceManagerException(String reason, Exception e) {
		super(reason, e);
		// TODO Auto-generated constructor stub
	}

	public ServiceManagerException(String reason) {
		super(reason);
		// TODO Auto-generated constructor stub
	}

}
