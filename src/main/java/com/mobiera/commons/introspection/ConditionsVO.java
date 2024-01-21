package com.mobiera.commons.introspection;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.mobiera.commons.vo.CommonVO;

@JsonInclude(Include.NON_NULL)
public class ConditionsVO implements CommonVO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4829352845298862370L;

	private List<ConditionVO> conditions;

	public List<ConditionVO> getConditions() {
		return conditions;
	}

	public void setConditions(List<ConditionVO> conditions) {
		this.conditions = conditions;
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
