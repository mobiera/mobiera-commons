package com.mobiera.commons.enums;

public enum TpRuleSection {
		
	BASIC_INFORMATION("Basic Information", "Basic Information");
	
		private TpRuleSection(String label, String description){
			this.label = label;
			this.description = description;
		}

		private int index;

		public int getIndex(){
			return this.index;
		}

		public static TpRuleSection getEnum(Integer index){
			if (index == null)
		return null;

			switch(index){
				case 0: return BASIC_INFORMATION;
				default: return null;
			}
		}
		
		public String getValue() {
			return this.name();
		}
		private String label;
		public String getLabel() {
			return label;
		}
		
		private String description;
		public String getDescription() {
			return description;
		}
		
		public String getName() {
			return this.toString();
		}

	
}
