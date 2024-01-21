package com.mobiera.commons.introspection;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.mobiera.commons.vo.CommonVO;

@JsonInclude(Include.NON_NULL)
public class MainVO implements CommonVO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4829352845298862370L;

	private String sectionEnum;
	private String filterForm;
	private String description;
	private String singularLabel;
	private String pluralLabel;
	private String newEntityLabel;
	private String newEntityDescription;
	
	public String getSectionEnum() {
		return sectionEnum;
	}

	public void setSectionEnum(String sectionEnum) {
		this.sectionEnum = sectionEnum;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	
	public String getSingularLabel() {
		return singularLabel;
	}

	public void setSingularLabel(String singularLabel) {
		this.singularLabel = singularLabel;
	}

	public String getPluralLabel() {
		return pluralLabel;
	}

	public void setPluralLabel(String pluralLabel) {
		this.pluralLabel = pluralLabel;
	}

	public String getNewEntityLabel() {
		return newEntityLabel;
	}

	public void setNewEntityLabel(String newEntityLabel) {
		this.newEntityLabel = newEntityLabel;
	}

	public String getNewEntityDescription() {
		return newEntityDescription;
	}

	public void setNewEntityDescription(String newEntityDescription) {
		this.newEntityDescription = newEntityDescription;
	}

	public String getFilterForm() {
		return filterForm;
	}

	public void setFilterForm(String filterForm) {
		this.filterForm = filterForm;
	}
	
	
	
	
	
	
}
