package com.mobiera.commons.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.mobiera.commons.enums.ServiceStatus;

@JsonInclude(Include.NON_NULL)
public abstract class ServiceStatusVO implements CommonVO {

	private static final long serialVersionUID = 7546227796132798728L;

	private Long entityId;
	private String entityName;
	private ServiceStatus serviceStatus;
	
	public final Long getEntityId() {
		return entityId;
	}
	public final void setEntityId(Long entityId) {
		this.entityId = entityId;
	}
	public final String getEntityName() {
		return entityName;
	}
	public final void setEntityName(String entityName) {
		this.entityName = entityName;
	}
	public final ServiceStatus getServiceStatus() {
		return serviceStatus;
	}
	public final void setServiceStatus(ServiceStatus serviceStatus) {
		this.serviceStatus = serviceStatus;
	}
	
	
}
