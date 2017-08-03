package com.hine.util;

import java.lang.annotation.*;

@Documented
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Column {
	public persistence ColumnType() default persistence.PERSISTENCE;
	public String ColumnName() default "";
}
