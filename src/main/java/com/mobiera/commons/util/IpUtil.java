package com.mobiera.commons.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class IpUtil {

	
	private static final String PATTERN = 
	        "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
	        "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
	        "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
	        "([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";

	private static Pattern pattern = null;
	
	private static IpUtil instance = null;
	
	public static IpUtil getInstance() {
		if (instance == null) instance = new IpUtil();
		return instance;
	}
	
	public static void main(String[] args) {
		System.out.println(getInstance().validateIp("267.0.0.1"));
	}

	
	public boolean validateIp(final String ip){          

		if (pattern == null) {
			pattern = Pattern.compile(PATTERN);
		}
	      Matcher matcher = pattern.matcher(ip);
	      return matcher.matches();             
	}
	
	
	
}
