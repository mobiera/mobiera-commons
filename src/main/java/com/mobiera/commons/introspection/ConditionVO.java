package com.mobiera.commons.introspection;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.mobiera.commons.vo.CommonVO;

@JsonInclude(Include.NON_NULL)
public class ConditionVO implements CommonVO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4829352845298862370L;

	private String field;
	private List<String> values;
	
	public List<String> getValues() {
		return values;
	}
	public void setValues(List<String> values) {
		this.values = values;
	}
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	
	
	
	
	
	/*
	 * "displaysIf" : { [ { 
	 * 			"attribute" : "type",
	 * 			"values" : [ "CAMPAIGN", "NOTIFICATION" ]		
	 *  }, { 
	 * 			"attribute" : "state",
	 * 			"values" : [ "DISABLED" ]		
	 *  }  ], [ { 
	 * 			"attribute" : "type",
	 * 			"values" : [ "CAMPAIGN", "NOTIFICATION" ]		
	 *  }, { 
	 * 			"attribute" : "state",
	 * 			"values" : [ "DISABLED" ]		
	 *  } 
	 *  
	 *  
	 *  ] }
	 */
	
	
}
