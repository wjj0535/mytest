package org.wangjj.practice.service2.service;

import com.alibaba.dubbo.config.annotation.Service;
import org.wangjj.practice.serviceapi.DemoService;

/**
 * ClassName: DemoServiceImpl <br/>
 * Function: <br/>
 * Reason:  <br/>
 * date: 2021/6/23 上午10:54 <br/>
 *
 * @author wangjunjie
 * @since JDK 1.8
 */
@Service(version = "1.0.0", interfaceName = "org.wangjj.practice.serviceapi.DemoService")
public class DemoServiceImpl implements DemoService {
    @Override
    public String getMessage() {
        return "this is my first dubbo, haha!";
    }
}
