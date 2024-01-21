package com.mobiera.commons.enums;

import java.io.Serializable;

public enum EntityState implements Serializable {

	DISABLED(0, "Disabled", "Disabled Entity", "999999"), 
	ENABLED(1, "Enabled", "Enabled Entity", "09e359"), 
	ARCHIVED(2, "Archived", "Archived Entity", "000000"),
	EDITING(3, "Editing", "Editing Entity", "0080ff"),
	PENDING(4, "Pending", "Pending Entity", "ff8bfe"),
	TESTING(5, "Testing", "Testing Entity", "690068"),
	CERTIFIED(6, "Certified", "Certified Entity", "b2ffb6"),
	REFUSED(7, "Refused", "Refused Entity", "ff2b2b"),
	PAUSED(8, "Paused", "Paused Entity", "f8ff00");

	
	private String label;
	private String description;
	private String rgbColor;
	
	private EntityState(Integer index, String label, String description, String rgbColor){
		this.index = index;
		this.label = label;
		this.description = description;
		this.rgbColor = rgbColor;
	}

	private Integer index;

	public Integer getIndex(){
		return this.index;
	}

	public static EntityState getEnum(Integer index){
		if (index == null)
	return null;

		switch(index){
			case 0: return DISABLED;
			case 1: return ENABLED;
			case 2: return ARCHIVED;
			case 3: return EDITING;
			case 4: return PENDING;
			case 5: return TESTING;
			case 6: return CERTIFIED;
			case 7: return REFUSED;
			case 8: return PAUSED;
					
			default: return null;
		}
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	

	public void setIndex(Integer index) {
		this.index = index;
	}

	public String getRgbColor() {
		return rgbColor;
	}

	public void setRgbColor(String rgbColor) {
		this.rgbColor = rgbColor;
	}
	
	
	public String getName() {
		return this.toString();
	}
	
	
	public String getValue() {
		return this.toString();
	}

}