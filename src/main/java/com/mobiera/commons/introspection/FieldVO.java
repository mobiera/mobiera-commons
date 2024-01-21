package com.mobiera.commons.introspection;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.mobiera.commons.enums.ClassType;
import com.mobiera.commons.enums.Knowledge;
import com.mobiera.commons.enums.Mode;
import com.mobiera.commons.enums.WidgetType;
import com.mobiera.commons.vo.CommonVO;

@JsonInclude(Include.NON_NULL)
public class FieldVO implements CommonVO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4829352845298862370L;

	private String name;
	private ClassType classType;
	private String className;
	private ClassType subClassType;
	private String subClassName;
	private List<String> inheritedSubClassNames;
	private String label;
	private String description;
	private Boolean required;
	private String section;
	private Mode mode;
	private WidgetType widgetType;
	private Integer minSize;
	private Integer maxSize;
	private String minValue;
	private String maxValue;
	private String defaultValue;
	private ClassType targetClassType;
	private String targetClassName;
	private List<String> allowedValues;
	private List<String> disallowedValues;
	private Knowledge knowledge;
	
	private List<ConditionsVO> displayWhen;
	private FilterVO filter;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public Boolean getRequired() {
		return required;
	}
	public void setRequired(Boolean required) {
		this.required = required;
	}
	public String getSection() {
		return section;
	}
	public void setSection(String section) {
		this.section = section;
	}
	public Mode getMode() {
		return mode;
	}
	public void setMode(Mode mode) {
		this.mode = mode;
	}
	
	public List<ConditionsVO> getDisplayWhen() {
		return displayWhen;
	}
	public void setDisplayWhen(List<ConditionsVO> displayWhen) {
		this.displayWhen = displayWhen;
	}
	public WidgetType getWidgetType() {
		return widgetType;
	}
	public void setWidgetType(WidgetType widgetType) {
		this.widgetType = widgetType;
	}
	
	public ClassType getClassType() {
		return classType;
	}
	public void setClassType(ClassType classType) {
		this.classType = classType;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public Integer getMinSize() {
		return minSize;
	}
	public void setMinSize(Integer minSize) {
		this.minSize = minSize;
	}
	public Integer getMaxSize() {
		return maxSize;
	}
	public void setMaxSize(Integer maxSize) {
		this.maxSize = maxSize;
	}
	public String getMinValue() {
		return minValue;
	}
	public void setMinValue(String minValue) {
		this.minValue = minValue;
	}
	public String getMaxValue() {
		return maxValue;
	}
	public void setMaxValue(String maxValue) {
		this.maxValue = maxValue;
	}
	public ClassType getTargetClassType() {
		return targetClassType;
	}
	public void setTargetClassType(ClassType targetClassType) {
		this.targetClassType = targetClassType;
	}
	public String getTargetClassName() {
		return targetClassName;
	}
	public void setTargetClassName(String targetClassName) {
		this.targetClassName = targetClassName;
	}
	public String getDefaultValue() {
		return defaultValue;
	}
	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}
	public FilterVO getFilter() {
		return filter;
	}
	public void setFilter(FilterVO filter) {
		this.filter = filter;
	}
	public List<String> getAllowedValues() {
		return allowedValues;
	}
	public void setAllowedValues(List<String> allowedValues) {
		this.allowedValues = allowedValues;
	}
	public List<String> getDisallowedValues() {
		return disallowedValues;
	}
	public void setDisallowedValues(List<String> disallowedValues) {
		this.disallowedValues = disallowedValues;
	}
	public ClassType getSubClassType() {
		return subClassType;
	}
	public void setSubClassType(ClassType subClassType) {
		this.subClassType = subClassType;
	}
	public String getSubClassName() {
		return subClassName;
	}
	public void setSubClassName(String subClassName) {
		this.subClassName = subClassName;
	}
	public List<String> getInheritedSubClassNames() {
		return inheritedSubClassNames;
	}
	public void setInheritedSubClassNames(List<String> inheritedSubClassNames) {
		this.inheritedSubClassNames = inheritedSubClassNames;
	}
	
	public Knowledge getKnowledge() {
		return knowledge;
	}
	public void setKnowledge(Knowledge knowledge) {
		this.knowledge = knowledge;
	}
	
	
	
	
	
	
}
