package com.mobiera.commons.introspection;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.LOCAL_VARIABLE;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(value={FIELD, LOCAL_VARIABLE})
public @interface Condition {

	String field() default "";

	String[] values();

}
