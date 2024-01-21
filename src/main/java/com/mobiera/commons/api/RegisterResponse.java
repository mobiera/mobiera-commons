package com.mobiera.commons.api;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.mobiera.commons.enums.EntityState;

@JsonInclude(Include.NON_NULL)
public class RegisterResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7960755744857629469L;
	
	private String id;
	private String app;
	private String registryId;
	private String errorMsg; 
	private Instant expireTs;
	private Long entityFk;

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public Instant getExpireTs() {
		return expireTs;
	}

	public void setExpireTs(Instant expireTs) {
		this.expireTs = expireTs;
	}

	public Long getEntityFk() {
		return entityFk;
	}

	public void setEntityFk(Long entityFk) {
		this.entityFk = entityFk;
	}

	


	
}
