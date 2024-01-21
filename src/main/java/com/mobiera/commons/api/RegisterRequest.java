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
public class RegisterRequest implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8087060009900936019L;
	
	
	private String id;
	private Long entityFk;	
	private String app;
	private String remoteAddr;
	
	
	
	
	
	public String getApp() {
		return app;
	}
	public void setApp(String app) {
		this.app = app;
	}
	public String getRemoteAddr() {
		return remoteAddr;
	}
	public void setRemoteAddr(String remoteAddr) {
		this.remoteAddr = remoteAddr;
	}
	
	public Long getEntityFk() {
		return entityFk;
	}
	public void setEntityFk(Long entityFk) {
		this.entityFk = entityFk;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	
	
	
}
