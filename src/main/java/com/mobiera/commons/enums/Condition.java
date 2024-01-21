package com.mobiera.commons.enums;

import java.io.Serializable;

public enum Condition implements Serializable {

	NULL(0), TRUE(1),FALSE(2);

	private Condition(Integer index){
		this.index = index;
	}

	private Integer index;

	public Integer getIndex(){
		return this.index;
	}

	public static Condition getEnum(Integer index){
		
		if (index == null)
	return null;

		switch(index){
			case 0: return NULL;
			case 1: return TRUE;
			case 2: return FALSE;
					
			default: return null;
		}
	}

}