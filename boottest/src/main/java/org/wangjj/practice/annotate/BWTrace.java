package org.wangjj.practice.annotate;

import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface BWTrace {
    String value() default "";
}
