package com.mobiera.commons.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.mobiera.commons.enums.EntityState;

@JsonInclude(Include.NON_NULL)
public abstract class EntityVO implements CommonVO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4139067175156509602L;
	
	private Long id;
	private EntityState state;
	private Long ownerId;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public EntityState getState() {
		return state;
	}

	public void setState(EntityState state) {
		this.state = state;
	}

	public Long getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(Long ownerId) {
		this.ownerId = ownerId;
	}
	
}
