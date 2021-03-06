package com.excilys.computerdatabase.util;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * MyDate is a annotation used to controller the date format of the dtos
 * MyDate is a personalized annotation
 * @see com.excilys.computerdatabase.util.DateValidator
 */
@Documented
@Constraint(validatedBy = DateValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface MyDate {
	String message() default "{DateTimeFormat.Computer.date}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
