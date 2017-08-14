package com.softcell.common.exceptions;

public class SoftCellException extends Exception{

	public SoftCellException(String reason) {
		super(reason);
	}

	public SoftCellException(Exception e) {

		super(e);
	}

	public SoftCellException(String reason, Exception e) {
		// TODO Auto-generated constructor stub
		
		super(reason, e);
	}

	
	
	
	
	

}
