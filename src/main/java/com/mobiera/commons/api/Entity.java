package com.mobiera.commons.api;

import java.io.Serializable;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.mobiera.commons.enums.EntityState;

@JsonInclude(Include.NON_NULL)
public class Entity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -184133180198557843L;

	
	private Long entityId;
	private UUID entityUUID;
	private EntityState entityState;
	public Long getEntityId() {
		return entityId;
	}
	public void setEntityId(Long entityId) {
		this.entityId = entityId;
	}
	public UUID getEntityUUID() {
		return entityUUID;
	}
	public void setEntityUUID(UUID entityUUID) {
		this.entityUUID = entityUUID;
	}
	public EntityState getEntityState() {
		return entityState;
	}
	public void setEntityState(EntityState entityState) {
		this.entityState = entityState;
	}
	
	
}
