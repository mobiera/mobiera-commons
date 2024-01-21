package com.mobiera.commons.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public abstract class QueueVO implements CommonVO {

	private static final long serialVersionUID = 7546227796132798729L;

	private String queueName;
	private Integer maxSize;
	private Integer currentSize;
	private Integer warningSize;
	private Integer alertSize;
	
	public Integer getMaxSize() {
		return maxSize;
	}
	public void setMaxSize(Integer maxSize) {
		this.maxSize = maxSize;
	}
	public Integer getCurrentSize() {
		return currentSize;
	}
	public void setCurrentSize(Integer currentSize) {
		this.currentSize = currentSize;
	}
	public Integer getWarningSize() {
		return warningSize;
	}
	public void setWarningSize(Integer warningSize) {
		this.warningSize = warningSize;
	}
	public Integer getAlertSize() {
		return alertSize;
	}
	public void setAlertSize(Integer alertSize) {
		this.alertSize = alertSize;
	}
	public String getQueueName() {
		return queueName;
	}
	public void setQueueName(String queueName) {
		this.queueName = queueName;
	}
	
	
}
