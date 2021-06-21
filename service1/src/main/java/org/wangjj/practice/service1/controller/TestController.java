package org.wangjj.practice.service1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName: TestController <br/>
 * Function: <br/>
 * Reason:  <br/>
 * date: 2018/9/4 下午8:38 <br/>
 *
 * @author wangjunjie
 * @since JDK 1.8
 */
@RestController
public class TestController {
    @GetMapping(value = "/req")
    public String test() {
        return "";
    }
}
