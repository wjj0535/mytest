package org.wangjj.practice.service1.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wangjj.practice.serviceapi.DemoService;

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

    @Reference(version = "1.0.0", check = false)
    private DemoService demoService;

    @GetMapping(value = "/req")
    public String test() {
//        return "1";
        return demoService.getMessage();
    }
}
