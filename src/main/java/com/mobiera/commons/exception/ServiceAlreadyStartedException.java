package com.mobiera.commons.exception;

public class ServiceAlreadyStartedException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -9163272677483822518L;

	public ServiceAlreadyStartedException(String s) {
		super(s);
	}
}
