package org.wangjj.practice.service2.service.middleground.v1.test;

import com.alibaba.fastjson.JSON;
import org.wangjj.practice.service2.annotation.middleground.MdAction;
import org.wangjj.practice.service2.service.middleground.BaseService;
import org.wangjj.practice.service2.service.middleground.StandProcess;

//@Service(value = "default_test_1.0")
@MdAction(code="test", version="1.0")
public class TestV1Service extends BaseService implements StandProcess {

    @Override
    public String handle(String data) {
        System.out.printf(JSON.toJSONString(data));
        return JSON.toJSONString(data);
    }
}
