package org.wangjj.practice.service1.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wangjj.practice.serviceapi.domain.TestModel;
import org.wangjj.practice.serviceapi.service.DemoService;

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
        TestModel testModel = new TestModel();
        testModel.setActionName("test");
        testModel.setMsg("this is a test!");
        return demoService.process(JSON.toJSONString(testModel));
    }
}
