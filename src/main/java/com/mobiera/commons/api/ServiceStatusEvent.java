package com.mobiera.commons.api;

import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.mobiera.commons.enums.EntityState;

@JsonInclude(Include.NON_NULL)
public class ServiceStatusEvent implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7960755744857629469L;
	
	private String id;
	private String type;
	private String registryId;
	private String log;
	private String app; // unnecessary, only for debugging purpose
	private Long entityFk; // unnecessary, only for debugging purpose
	private EntityState state;
	
	
	public String getLog() {
		return log;
	}

	public void setLog(String log) {
		this.log = log;
	}

	

	public String getApp() {
		return app;
	}

	public void setApp(String app) {
		this.app = app;
	}

	

	public String getRegistryId() {
		return registryId;
	}

	public void setRegistryId(String registryId) {
		this.registryId = registryId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Long getEntityFk() {
		return entityFk;
	}

	public void setEntityFk(Long entityFk) {
		this.entityFk = entityFk;
	}

	public EntityState getState() {
		return state;
	}

	public void setState(EntityState state) {
		this.state = state;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	

	
	
}
