package com.mobiera.commons.enums;

public enum ServiceSanity {
	SANE(0, "Sane", "Sane", "09e359"), 
	INSANE(1, "Insane", "Insane", "ff8bfe"),
	UNKNOWN(2, "Unknown", "Unknown", "ff2b2b");
	
	private String label;
	private String description;
	private String rgbColor;
	
	private ServiceSanity(Integer index, String label, String description, String rgbColor){
		this.index = index;
		this.label = label;
		this.description = description;
		this.rgbColor = rgbColor;
	}

	private Integer index;

	public Integer getIndex(){
		return this.index;
	}

	public static ServiceSanity getEnum(Integer index){
		if (index == null)
	return null;

		switch(index){
			case 0: return SANE;
			case 1: return INSANE;
			case 2: return UNKNOWN;
			
					
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
