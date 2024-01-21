package com.mobiera.commons.introspection;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.jboss.logging.Logger;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.mobiera.commons.enums.ClassType;
import com.mobiera.commons.enums.Logic;
import com.mobiera.commons.exception.BadArgumentException;
import com.mobiera.commons.exception.NullArgumentException;



public abstract class Introspector {
	
	
	private static ConcurrentHashMap<String, List<EnumValueVO>> keyPairs = new ConcurrentHashMap<String, List<EnumValueVO>>();
	private static ConcurrentHashMap<String, List<String>> valueKeyPairs = new ConcurrentHashMap<String, List<String>>();
	private static ConcurrentHashMap<String, List<FieldVO>> voAttributesKeyPairs = new ConcurrentHashMap<String, List<FieldVO>>();
	private static ConcurrentHashMap<String, MainVO> voMains = new ConcurrentHashMap<String, MainVO>();
	private static Logger logger = Logger.getLogger(Introspector.class);
	private static ArrayList<String> defaultEnumPackages;
	private static ArrayList<String> defaultVOPackages;
	
	public List<Field> getFields(Class<?> clazz) {
		
		List<Field> fields = new ArrayList<Field>();
		
		if (clazz == null) return fields;
		if (clazz.getSuperclass() != null) {
			fields.addAll(this.getFields(clazz.getSuperclass()));
		}
		Collections.addAll(fields, clazz.getDeclaredFields());

		return fields;
	}
	
	public List<FieldVO> listAttributeVOs(List<String> packageNames, String voName) throws ClassNotFoundException {
		
		
		List<FieldVO> attributes = null;
		synchronized (voAttributesKeyPairs) {
			attributes = voAttributesKeyPairs.get(voName);
		}
		if (attributes != null) return attributes;
		
		Class<?> clazz = null;
		
		for (String packageName: packageNames) {
			try {
				clazz = Class.forName(packageName + "." + voName);
			} catch (ClassNotFoundException e1) {
				
			}
			if (clazz != null) break;
		}
		
		if (clazz == null) {
			throw new ClassNotFoundException(voName);
		}
		
		List<Field> fss = this.getFields(clazz);
		
		
		attributes = new ArrayList<FieldVO>();
		for (Field f: fss) {
			if (!f.getName().equals("serialVersionUID")) {
				
				JsonIgnore jsonIgnore = f.getAnnotation(JsonIgnore.class);
				
				if (jsonIgnore == null) {
					FieldVO attr = new FieldVO();
					attr.setName(f.getName());
					attr.setClassType(getClassType(f.getType()));
					attr.setClassName(f.getType().getSimpleName());
					
					if (attr.getClassName().equals("List")) {
						Type typef = f.getGenericType();
						if (typef instanceof ParameterizedType) {
							ParameterizedType ptypef = (ParameterizedType) typef;
							Type tp = ptypef.getActualTypeArguments()[0];
							
							if (!(tp instanceof ParameterizedType)) {
								Class<?> subClass = (Class<?>)tp;
								ClassType subClassType = this.getClassType(subClass);
								switch (subClassType) {
								case VO: {
									attr.setSubClassType(subClassType);
									attr.setSubClassName(subClass.getSimpleName());
									// check for Json annotations
									
									JsonSubTypes jst = subClass.getAnnotation(JsonSubTypes.class);
									if (jst != null) {
										JsonSubTypes.Type[] types = jst.value();
										if (types != null) {
											List<String> inheritedSubClassNames = new ArrayList<String>(types.length);
											for (int t=0; t<types.length; t++) {
												JsonSubTypes.Type current = types[t];
												inheritedSubClassNames.add(current.value().getSimpleName());
											}
											attr.setInheritedSubClassNames(inheritedSubClassNames);
										}
										
										
									}
									break;
									
									
								}
								case ENUM:
								case NATIVE: {
									attr.setSubClassType(subClassType);
									attr.setSubClassName(subClass.getSimpleName());
									
								}
								}
							}
							
							
							
							
							
							
						}
					}
					UI ui = f.getAnnotation(UI.class);
					if (ui != null) {
						attr.setMode(ui.mode());
						attr.setWidgetType(ui.widgetType());
						attr.setDescription(ui.description());
						attr.setLabel(ui.label());
					}
					Required required = f.getAnnotation(Required.class);
					if (required != null) {
						attr.setRequired(required.always());
					} 
					Expertise expertise = f.getAnnotation(Expertise.class);
					if (expertise != null) {
						attr.setKnowledge(expertise.knowledge());
					} 
					Section section = f.getAnnotation(Section.class);
					if (section != null) {
						attr.setSection(section.name());
					}
					TargetClass targetClass = f.getAnnotation(TargetClass.class);
					if (targetClass != null) {
						attr.setTargetClassType(targetClass.type());
						attr.setTargetClassName(targetClass.name());
						
					}
					
					Validator validator = f.getAnnotation(Validator.class);
					if (validator != null) {
						// 
						if (attr.getClassName().equals("List")) {
							if(validator.minSize() >= 0) attr.setMinSize(validator.minSize());
							if(validator.maxSize() >= 0) attr.setMaxSize(validator.maxSize());
						} else if (attr.getClassName().equals("String")) {
							if(validator.minSize() >= 0) attr.setMinSize(validator.minSize());
							if(validator.maxSize() >= 0) attr.setMaxSize(validator.maxSize());
						} else if (attr.getClassName().equals("byte[]")) {
							if(validator.minSize() >= 0) attr.setMinSize(validator.minSize());
							if(validator.maxSize() >= 0) attr.setMaxSize(validator.maxSize());
						} else if ( (attr.getClassName().equals("Long") 
								|| attr.getClassName().equals("Integer")
								|| attr.getClassName().equals("Double") 
								|| attr.getClassName().equals("Float")
								|| attr.getClassName().equals("Short"))) {
							if (!validator.minValue().isEmpty()) attr.setMinValue(validator.minValue());
							if (!validator.maxValue().isEmpty()) attr.setMaxValue(validator.maxValue());
						} else if (attr.getClassType().equals(ClassType.ENUM)) {
							if (validator.allowedValues().length>0) {
								attr.setAllowedValues(Arrays.asList(validator.allowedValues()));
							}
							if (validator.disallowedValues().length>0) {
								attr.setDisallowedValues(Arrays.asList(validator.disallowedValues()));
							}
						}
						
						
						if (!validator.defaultValue().isEmpty()) attr.setDefaultValue(validator.defaultValue());
						
						
						
					}
					Filter filter = f.getAnnotation(Filter.class);
					if (filter != null) {
						FilterVO filterVO = new FilterVO();
						filterVO.setField(filter.field());
						filterVO.setLogic(Logic.valueOf(filter.logic()));
						filterVO.setValues(Arrays.asList(filter.values()));
						attr.setFilter(filterVO);
					}
					DisplayWhen when = f.getAnnotation(DisplayWhen.class);
					if (when != null) {
						Conditions[] conditionsArray = when.value();
						List<ConditionsVO> andConditions = new ArrayList<ConditionsVO>(conditionsArray.length);
						if (conditionsArray != null) {
							for (int c=0; c<conditionsArray.length; c++) {
								Conditions conditions = conditionsArray[c];
								Condition[] conditionArray = conditions.value();
								if (conditionArray != null) {
									ConditionsVO conditionsVO = new ConditionsVO();
									List<ConditionVO> conditionsVOlist = new ArrayList<ConditionVO>(conditionArray.length);
									
									for (int d=0; d<conditionArray.length; d++) {
										Condition condition = conditionArray[d];
										ConditionVO conditionVO = new ConditionVO();
										conditionVO.setField(condition.field());
										conditionVO.setValues(Arrays.asList(condition.values()));
										conditionsVOlist.add(conditionVO);
									}
									conditionsVO.setConditions(conditionsVOlist);
									andConditions.add(conditionsVO);
								}
							}
						}
						attr.setDisplayWhen(andConditions);
					}
					attributes.add(attr);
				
				}
				
			}	
			
			
		}
		
		synchronized (voAttributesKeyPairs) {
			voAttributesKeyPairs.put(voName, attributes);
		}
		
		return attributes;
		
	}

	
	
	private ClassType getClassType(Class<?> type) {
		
		
		if (!type.isPrimitive()) {
			if (type.getPackage() != null) {
				List<String> enumPackages = getEnumPackages();
				
				for (String enumPackage: enumPackages) {
					if (type.getPackage().getName().equals(enumPackage)) {
						return ClassType.ENUM;
					} 
				}
				
				List<String> voPackages = getVOPackages();
				
				for (String voPackage: voPackages) {
					if (type.getPackage().getName().equals(voPackage)) {
						return ClassType.VO;
					} 
				}
			}
		}
		
		return ClassType.NATIVE;
		
	}
	
	public List<EnumValueVO> listEnumValueVOs(List<String> packageNames, String enumName) throws ClassNotFoundException {
		return this.listEnumValueVOs(packageNames, enumName, null);
	}
	
	public List<EnumValueVO> listEnumValueVOs(List<String> packageNames, String enumName, Object[] consts) throws ClassNotFoundException {

		List<EnumValueVO> result = null;
				
		synchronized (keyPairs) {
			result = keyPairs.get(enumName);
		}
				
		if (result != null) return result;
		
		Class<?> clazz = null;
		
		for (String packageName: packageNames) {
			try {
				clazz = Class.forName(packageName + "." + enumName);
				
				logger.warn(packageName + "." + enumName);
			} catch (ClassNotFoundException e1) {
				logger.warn(packageName + "." + enumName + " classNotFound");
			}
			if (clazz != null) break;
		}
		
		if (clazz == null) {
			throw new ClassNotFoundException(enumName);
		}
		
		if (consts == null) {
			consts = clazz.getEnumConstants();
		}
		 
		result = new ArrayList<EnumValueVO>(consts.length);
		
		
		for (int i=0; i<consts.length; i++) {
			EnumValueVO vo = new EnumValueVO();
			Object c = consts[i];
			Class<?> sub = c.getClass();
			Method mth = null;
			
			
			try {
				mth = sub.getDeclaredMethod("getName");
				String name;
				try {
					name = (String) mth.invoke(c);
					vo.setName(name);
					
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) { }
			} catch (NoSuchMethodException | SecurityException e) { } 
		    if (vo.getName() == null) vo.setName(c.toString());
		    
		    
		    try {
				mth = sub.getDeclaredMethod("getValue");
				String value = null;
				
				try {
					value = (String) mth.invoke(c);
					vo.setValue(value);
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) { }
			} catch (NoSuchMethodException | SecurityException e) { }
		    
		    if (vo.getValue() == null) vo.setValue(c.toString());
			
		    
		    try {
				mth = sub.getDeclaredMethod("getLabel");
				String label = null;
				try {
					label = (String) mth.invoke(c);
					vo.setLabel(label);
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {}
				
			} catch (NoSuchMethodException | SecurityException e) {}
		    
		    try {
				mth = sub.getDeclaredMethod("getDescription");
				String description = null;
				try {
					description = (String) mth.invoke(c);
					 vo.setDescription(description);
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {}
			   
			} catch (NoSuchMethodException | SecurityException e) { }
		    
		    try {
				mth = sub.getDeclaredMethod("getRgbColor");
				String rgbColor = null;
				try {
					rgbColor = (String) mth.invoke(c);
					 vo.setRgbColor(rgbColor);
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {}
			   
			} catch (NoSuchMethodException | SecurityException e) { }
		    
		    try {
				mth = sub.getDeclaredMethod("isShowByDefault");
				boolean showByDefault = false;
				try {
					showByDefault = (boolean) mth.invoke(c);
					 vo.setShowByDefault(showByDefault);
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {}
			   
			} catch (NoSuchMethodException | SecurityException e) { }
		    
		    
		    
		    
		    result.add(vo);
		}
		synchronized (keyPairs) {
			keyPairs.put(enumName, result);
		}
		return result;
	}
	
	
	public List<String> listEnumValues(List<String> packageNames, String enumName) throws ClassNotFoundException {

		
		List<String> result = null;
				
		synchronized (valueKeyPairs) {
			result = valueKeyPairs.get(enumName);
		}
				
		if (result != null) return result;
		
		Class<?> clazz = null;
		
		for (String packageName: packageNames) {
			try {
				clazz = Class.forName(packageName + "." + enumName);
			} catch (ClassNotFoundException e1) {
				
			}
			if (clazz != null) break;
		}
		
		if (clazz == null) {
			throw new ClassNotFoundException(enumName);
		}
		
		
		
		
		Object[] consts = clazz.getEnumConstants();
		result = new ArrayList<String>(consts.length);
		
		
		for (int i=0; i<consts.length; i++) {
			//EnumValueVO vo = new EnumValueVO();
			Object c = consts[i];
			
		    result.add(c.toString());
		}
		synchronized (valueKeyPairs) {
			valueKeyPairs.put(enumName, result);
		}
		return result;
	}

	public MainVO getMainVO(List<String> packageNames, String voName) throws ClassNotFoundException {
		MainVO main = null;
		synchronized (voMains) {
			main = voMains.get(voName);
		}
		if (main != null) return main;
		
		Class<?> clazz = null;
		
		for (String packageName: packageNames) {
			try {
				clazz = Class.forName(packageName + "." + voName);
			} catch (ClassNotFoundException e1) {
				
			}
			if (clazz != null) break;
		}
		
		if (clazz == null) {
			throw new ClassNotFoundException(voName);
		}
		
		
		main = new MainVO();
		
		SectionEnum se = clazz.getAnnotation(SectionEnum.class);
		if (se != null) {
			main.setSectionEnum(se.name());
		}
		
		
		
		
		Description desc = clazz.getAnnotation(Description.class);
		if (desc != null) {
			main.setDescription(desc.data());
		}
		
		Label label = clazz.getAnnotation(Label.class);
		if (label != null) {
			main.setPluralLabel(label.plural());
			main.setSingularLabel(label.singular());
			main.setNewEntityDescription(label.newEntityDescription());
			main.setNewEntityLabel(label.newEntityLabel());
		}
		
		
		synchronized (voMains) {
			voMains.put(voName, main);
		}
		
		return main;
	}
	
	
	
	public void checkArgs(Object vo, boolean debug) throws NullArgumentException, BadArgumentException, ClassNotFoundException {
		
		Class<?> clazz = vo.getClass();
		Field[] fs = clazz.getDeclaredFields();
		
		for (int i=0; i<fs.length; i++) {
			Field f = fs[i];
			if (!f.getName().equals("serialVersionUID")) {
				
				JsonIgnore jsonIgnore = f.getAnnotation(JsonIgnore.class);
				
				if (jsonIgnore == null) {
					
					if (debug) logger.info("checkArgs: processing " + f.getName());
					Object fieldValue = this.getFieldValue(vo, f.getName());
					Required requiredAnnotation = f.getAnnotation(Required.class);
					boolean required = ((requiredAnnotation != null) && requiredAnnotation.always());
					
					if (debug) logger.info("checkArgs: processing " + f.getName() + " required: " + required);
					
					boolean displayed = false;
					
					DisplayWhen when = f.getAnnotation(DisplayWhen.class);
					
					if (when != null) {
						if (debug) logger.info("checkArgs: processing " + f.getName() + " displayWhen not null");
						
						Conditions[] conditionsArray = when.value();
						if (debug) logger.info("checkArgs: processing " + f.getName() + " displayWhen not null " + conditionsArray.length + " conditions");
						
						if (conditionsArray != null) {
							for (int c=0; c<conditionsArray.length; c++) {
								Conditions conditions = conditionsArray[c];
								Condition[] conditionArray = conditions.value();
								if (debug) logger.info("checkArgs: processing " + f.getName() + " displayWhen not null " + conditionsArray.length + " conditions, process #" + c);
								
								if (conditionArray != null) {
									
									for (int d=0; d<conditionArray.length; d++) {
										Condition condition = conditionArray[d];
										String currentFieldName = condition.field();
										if (debug) logger.info("checkArgs: processing " + f.getName() + " displayWhen not null " + conditionsArray.length + " conditions, process #" + c + " fieldName: " + currentFieldName);
										
										try {
											Field currentField = clazz.getDeclaredField(currentFieldName);
											Object currentFieldValue = this.getFieldValue(vo, currentFieldName);
											List<String> allowedValues = Arrays.asList(condition.values());
											Class<?> currentFieldClass = currentField.getType();
											String currentFieldClassName = currentFieldClass.getSimpleName();
											ClassType classType = this.getClassType(currentFieldClass);
											if (debug) logger.info("checkArgs: processing " + f.getName() + " displayWhen not null " + conditionsArray.length + " conditions, process #" + c + " fieldName: " + currentField.getName() + " classType: " + classType + " currentFieldClassName: " + currentFieldClassName);
											
											switch (classType) {
												case ENUM: {
													List<String> enumValues = this.listEnumValues(getEnumPackages(), currentFieldClassName);
													
													for (String currentCondValue: allowedValues) {
														if (debug) logger.info("checkArgs: processing " + f.getName() + " displayWhen not null " + conditionsArray.length + " conditions, process #" + c + " fieldName: " + currentFieldName + " classType: " + classType
																+ " currentCondValue: " + currentCondValue + " currentFieldValue: " + currentFieldValue);
														
														
														if (currentCondValue.isEmpty() && (currentFieldValue == null)) {
															displayed = true;
														} else {
															displayed = false;
														}
														
														if ((currentFieldValue!= null) && ( currentCondValue.equals(currentFieldValue.toString()))) {
															displayed = true;
														} else {
															displayed = false;
														}
														if (displayed) {
															break;
														}
													}
													break;
												}
												case NATIVE: {
													if (debug) logger.info("checkArgs: processing " + f.getName() + " displayWhen not null " + conditionsArray.length + " conditions, process #" + c + " fieldName: " + currentFieldName + " classType: " + classType + " currentFieldClassName: " + currentFieldClassName
															+ " currentFieldValue: " + currentFieldValue);
													
													if (currentFieldClassName.equals("boolean") || currentFieldClassName.equals("Boolean")) {
														if (debug) logger.info("checkArgs: processing " + f.getName() + " displayWhen not null " + conditionsArray.length + " conditions, process #" + c + " fieldName: " + currentFieldName + " classType: " + classType + " currentFieldClassName: " + currentFieldClassName
																+ " currentFieldValue: " + currentFieldValue + " boolean");
														
														for (String currentCondValue: allowedValues) {
															if (debug) logger.info("checkArgs: processing " + f.getName() + " displayWhen not null " + conditionsArray.length + " conditions, process #" + c + " fieldName: " + currentFieldName + " classType: " + classType
																	+ " currentCondValue: " + currentCondValue + " currentFieldValue: " + currentFieldValue);
															
															
															if (currentCondValue.isEmpty() && (currentFieldValue == null)) {
																displayed = true;
															} else {
																displayed = false;
															}
															
															if ((currentFieldValue!= null) && ( new Boolean(currentCondValue).equals(currentFieldValue))) {
																displayed = true;
															} else {
																displayed = false;
															}
															if (displayed) {
																break;
															}
														}
													} else if (currentFieldClassName.equals("String")) {
														for (String currentCondValue: allowedValues) {
															if (debug) logger.info("checkArgs: processing " + f.getName() + " displayWhen not null " + conditionsArray.length + " conditions, process #" + c + " fieldName: " + currentFieldName + " classType: " + classType
																	+ " currentCondValue: " + currentCondValue + " currentFieldValue: " + currentFieldValue);
															
															
															if (currentCondValue.isEmpty() && (currentFieldValue == null)) {
																displayed = true;
															} else {
																displayed = false;
															}
															
															if ((currentFieldValue!= null) && ( currentCondValue.equals(currentFieldValue))) {
																displayed = true;
															} else {
																displayed = false;
															}
															if (displayed) {
																break;
															}
														}
													} else if (currentFieldClassName.equals("List")) {
														for (String currentCondValue: allowedValues) {
															if (debug) logger.info("checkArgs: processing " + f.getName() + " displayWhen not null " + conditionsArray.length + " conditions, process #" + c + " fieldName: " + currentFieldName + " classType: " + classType
																	+ " currentCondValue: " + currentCondValue + " currentFieldValue: " + currentFieldValue);
															
															
															if (currentCondValue.isEmpty() && (currentFieldValue == null)) {
																displayed = true;
															} else {
																displayed = false;
															}
															
															if ((currentFieldValue!= null) && ( ((List)currentFieldValue)).size() == 0) {
																displayed = true;
															} else {
																displayed = false;
															}
															if (displayed) {
																break;
															}
														}
													} else if (currentFieldClassName.equals("int") || currentFieldClassName.equals("Integer")
															|| currentFieldClassName.equals("long") || currentFieldClassName.equals("Long")
															|| currentFieldClassName.equals("float") || currentFieldClassName.equals("Float")
															|| currentFieldClassName.equals("double") || currentFieldClassName.equals("Double")
															|| currentFieldClassName.equals("short") || currentFieldClassName.equals("Short")) {
														for (String currentCondValue: allowedValues) {
															
															if (currentCondValue.isEmpty() && (currentFieldValue == null)) {
																displayed = true;
															} else {
																displayed = false;
															}
															if (currentFieldValue!= null) {
																
																try {
																	Double currentCondValueDouble = new Double(currentCondValue);
																	Double currentFieldValueDouble = (double)currentFieldValue;
																	if (currentCondValueDouble.equals(currentFieldValueDouble)) {
																		displayed = true;
																	}
																} catch (Exception e) {
																	displayed = false;
																}
																
															}
															
														if (displayed) {
															break;
														}	
														}
													}
													
													break;
												}
												case VO: {
													// ignore
													break;
												}
												default: {
													
													break;
												}
											}
											
										} catch (NoSuchFieldException | SecurityException e) {
											throw new BadArgumentException(clazz.getSimpleName() + "." + f.getName() + " " + currentFieldName);
										}
										
									if (!displayed) {
										// get out if one of the conditions is not satisfied
										break;
									}
									}
									
								}
								if (displayed) {
									// get out if one of the conditions is satisfied
									break;
								}
							}
						}
					} else {
						displayed = true;
					}
					if (debug) logger.info("checkArgs: processing " + f.getName() + " displayed: " + displayed);
					
					if (required && displayed && (fieldValue == null)) {
						throw new NullArgumentException(clazz.getSimpleName() + "." + f.getName());
					}
					
					Validator validator = f.getAnnotation(Validator.class);
					
					if ((validator != null) && (fieldValue != null)) {
						String defaultValue = validator.defaultValue();
						String minValue = validator.minValue();
						String maxValue = validator.maxValue();
						int minSize = validator.minSize();
						int maxSize = validator.maxSize();
						Class<?> fieldClass = f.getType();
						String fieldClassName = fieldClass.getSimpleName();
						ClassType classType = this.getClassType(fieldClass);
						
					
						
						switch (classType) {
						case NATIVE: { // byte[]
							if (fieldClassName.equals(String.class.getSimpleName())) {
								String string = (String)fieldValue;
								if ((required) && (minSize>=0)) {
									if (string.length()<minSize) {
										throw new BadArgumentException(clazz.getSimpleName() + "." + f.getName() + " required minSize: " + minSize);
									}
								}
								if (maxSize >=0) {
									if (string.length()>maxSize) {
										throw new BadArgumentException(clazz.getSimpleName() + "." + f.getName() + " required maxSize: " + maxSize);
									}
								}
								
							} else if (fieldClassName.equals("byte[]")) {
								byte[] bytes = (byte[])fieldValue;
								if ((required) && (minSize>=0)) {
									if (bytes.length<minSize) {
										throw new BadArgumentException(clazz.getSimpleName() + "." + f.getName() + " required minSize: " + minSize);
									}
								}
								if (maxSize >=0) {
									if (bytes.length>maxSize) {
										throw new BadArgumentException(clazz.getSimpleName() + "." + f.getName() + " required maxSize: " + maxSize);
									}
								}
								
							} else if (fieldClassName.equals("int") || fieldClassName.equals("Integer")
									|| fieldClassName.equals("long") || fieldClassName.equals("Long")
									|| fieldClassName.equals("float") || fieldClassName.equals("Float")
									|| fieldClassName.equals("double") || fieldClassName.equals("Double")
									|| fieldClassName.equals("short") || fieldClassName.equals("Short")) {
								Double value = null;
								
								if (fieldValue instanceof Integer) {
									value = ((Integer)fieldValue).doubleValue();
								} else if (fieldValue instanceof Long) {
									value = ((Long)fieldValue).doubleValue();
								} else if (fieldValue instanceof Float) {
									value = ((Float)fieldValue).doubleValue();
								} else if (fieldValue instanceof Double) {
									value = ((Double)fieldValue);
								} else if (fieldValue instanceof Short) {
									value = ((Short)fieldValue).doubleValue();
								}
								
								
								if (!(minValue.isEmpty() || maxValue.isEmpty())) {
									Double minValueDouble = new Double(minValue);
									Double maxValueDouble = new Double(maxValue);
									if (value.compareTo(minValueDouble) < 0) {
										throw new BadArgumentException(clazz.getSimpleName() + "." + f.getName() + " required minValue: " + minValue);
									}
									if (value.compareTo(maxValueDouble) > 0) {
										throw new BadArgumentException(clazz.getSimpleName() + "." + f.getName() + " required maxValue: " + maxValue);
									}
								}
							} else if (fieldClassName.equals("List")) {
								// check subType
								List list = (List) fieldValue;
								if ((required) && (minSize>=0)) {
									if (list.size()<minSize) {
										throw new BadArgumentException(clazz.getSimpleName() + "." + f.getName() + " required minSize: " + minSize);
									}
								}
								if (maxSize >= 0) {
									if (list.size()>maxSize) {
										throw new BadArgumentException(clazz.getSimpleName() + "." + f.getName() + " required maxSize: " + maxSize);
									}
								}
								
								
								Type typef = f.getGenericType();
								if (typef instanceof ParameterizedType) {
									Class<?> subClass = ((Class<?>)((ParameterizedType)
											typef)
											.getActualTypeArguments()[0])
											;
									ClassType subClassType = this.getClassType(subClass);
									if (subClassType.equals(ClassType.VO)) {
										if (debug) logger.info("found subtype in List: " + subClassType);
										
										for (Object o: list) {
											if (debug) logger.info("found subtype in List: " + subClassType + " checking object " + o);
											
											this.checkArgs(o, debug);
										}
									}
									
								}
								
							}
								
							break;
						}
						case ENUM: {
							
							String[] allowedValues = validator.allowedValues();
							String[] disallowedValues = validator.disallowedValues();
							
							//List<String> enumValues = this.listEnumValues(getEnumPackages(), fieldClassName);
							boolean foundAllowed = false;
							
							
							if (allowedValues.length > 0) {
								for (String currentCondValue: allowedValues) {
									if (debug) logger.info("checkArgs: processing allowedValues " + f.getName() + " validator " + currentCondValue);
									if ((fieldValue!= null) && ( currentCondValue.equals(fieldValue.toString()))) {
										foundAllowed = true;
									}
									if (foundAllowed) {
										break;
									}
								}
								
								if (!foundAllowed) {
									throw new BadArgumentException(clazz.getSimpleName() + "." + f.getName() + " value " + fieldValue.toString() + " not allowed");

								}
							}
							
							if (disallowedValues.length > 0) {
								for (String currentCondValue: disallowedValues) {
									if (debug) logger.info("checkArgs: processing disallowedValues " + f.getName() + " validator " + currentCondValue);
									if ((fieldValue!= null) && ( currentCondValue.equals(fieldValue.toString()))) {
										throw new BadArgumentException(clazz.getSimpleName() + "." + f.getName() + " value " + fieldValue.toString() + " not allowed");

									}
								}
							}
							
							break;
						}
						case VO: {
							// ignore
							break;
						}
						default: {
							break;
						}
						}
						
						
					}
					
				}
			}
		}
	}
	
	protected List<String> getEnumPackages() {
		if (defaultEnumPackages == null) {
			defaultEnumPackages = new ArrayList<String>();
			defaultEnumPackages.add(com.mobiera.commons.enums.EntityState.class.getPackage().getName());
		}
		return defaultEnumPackages;
	}

	protected List<String> getVOPackages() {
		if (defaultVOPackages == null) {
			defaultVOPackages = new ArrayList<String>();
			defaultVOPackages.add(com.mobiera.commons.vo.EntityVO.class.getPackage().getName());
		}
		return defaultVOPackages;
	}

	private Object getFieldValue(Object vo, String fieldName)  {
		Class<?> clazz = vo.getClass();
		Method method;
		try {
			method = clazz.getMethod("get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1, fieldName.length()));
			Object object;
			try {
				object = method.invoke(vo);
				return object;	
			} catch (IllegalAccessException e) {
				logger.error(e);
			} catch (IllegalArgumentException e) {
				logger.error(e);
			} catch (InvocationTargetException e) {
				logger.error(e);
			}
			//Class<?> objectClass = f.getDeclaringClass();
			
		} catch (NoSuchMethodException e) {
			logger.error(e);
		} catch (SecurityException e) {
			logger.error(e);
		}
		
		return null;	
	}

	
}
