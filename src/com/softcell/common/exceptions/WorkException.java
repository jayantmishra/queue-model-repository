package com.softcell.common.exceptions;

public class WorkException extends SoftCellException {

	public WorkException(Exception e) {
		super(e);
		// TODO Auto-generated constructor stub
	}

	public WorkException(String reason, Exception e) {
		super(reason, e);
		// TODO Auto-generated constructor stub
	}

	public WorkException(String reason) {
		super(reason);
		// TODO Auto-generated constructor stub
	}

}
