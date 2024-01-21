package com.mobiera.commons.api;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;


@JsonInclude(Include.NON_NULL)
public class ErrorResponse implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8087060009900936019L;
	
	
	private String error;
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	
	
	
	public static ErrorResponse build(String error) {
		ErrorResponse err = new ErrorResponse();
		err.setError(error);
		return err;
	}
	
	
	
}
