package org.wangjj.practice.service2.annotation.supplier;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface Action {
    String providerCode()  default "";
    String name()  default "";
    String version()  default "";
}
