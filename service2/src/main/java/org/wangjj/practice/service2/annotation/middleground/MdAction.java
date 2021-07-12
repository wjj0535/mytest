package org.wangjj.practice.service2.annotation.middleground;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface MdAction {
    String code()  default "";
    String version()  default "";
}
