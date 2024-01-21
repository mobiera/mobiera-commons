package com.mobiera.commons.introspection;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.mobiera.commons.vo.CommonVO;

@JsonInclude(Include.NON_NULL)
public class EnumValueVO implements CommonVO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4829352845298862370L;

	private String name;
	private String value;
	private String label;
	private String description;
	private String rgbColor;
	private Boolean showByDefault;
	//private Integer index;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
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
	public void setShowByDefault(boolean isShown) {
		showByDefault = isShown;
	}
	public Boolean getShowByDefault() {
		return showByDefault;
	}
	public void setShowByDefault(Boolean showByDefault) {
		this.showByDefault = showByDefault;
	}
	public String getRgbColor() {
		return rgbColor;
	}
	public void setRgbColor(String rgbColor) {
		this.rgbColor = rgbColor;
	}
	
	
	
	
	
}
