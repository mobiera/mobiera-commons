package com.mobiera.commons.introspection;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.LOCAL_VARIABLE;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Retention(RetentionPolicy.RUNTIME)
@Target(value={FIELD, LOCAL_VARIABLE})
public @interface Validator {

	int minSize() default -1;
	int maxSize() default -1;

	String minValue() default "";
	String maxValue() default "";
	String defaultValue() default "";
	
	String[] allowedValues() default {};
	String[] disallowedValues() default {};
	
}
