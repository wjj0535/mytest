package org.wangjj.practice.service2.service;

import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Service;
import org.wangjj.practice.service2.annotation.Action;

@Service(value = "default_test_1.0")
//@Action(providerCode = "", name="test", version="1.0")
public class TestService implements StandProcess{

    @Override
    public String handle(String data) {
        System.out.printf(JSON.toJSONString(data));
        return JSON.toJSONString(data);
    }
}
