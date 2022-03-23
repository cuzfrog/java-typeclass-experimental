package com.github.cuzfrog.ap;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.FIELD, ElementType.TYPE})
@Documented
@Retention(RetentionPolicy.SOURCE)
public @interface Implementation {
    /** The typeclass interface */
    Class<?>[] value() default {};
}
