package com.mobiera.commons.util;

public class LockObjUtil {

	private static Object jmsContextLockObj = new Object();

	public static Object getJmsContextLockObj() {
		return jmsContextLockObj;
	}

	public static void setJmsContextLockObj(Object jmsContextLockObj) {
		LockObjUtil.jmsContextLockObj = jmsContextLockObj;
	}

	
}
