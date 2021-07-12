package org.wangjj.practice.service2.rpcprovider;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.wangjj.practice.service2.service.ActionManager;
import org.wangjj.practice.service2.service.middleground.StandProcess;
import org.wangjj.practice.serviceapi.busiaction.ActionInfo;
import org.wangjj.practice.serviceapi.service.DemoService;

/**
 * ClassName: DemoServiceImpl <br/>
 * Function: <br/>
 * Reason:  <br/>
 * date: 2021/6/23 上午10:54 <br/>
 *
 * @author wangjunjie
 * @since JDK 1.8
 */
@Service(version = "1.0.0", interfaceName = "org.wangjj.practice.serviceapi.service.DemoService")
public class DemoServiceImpl implements DemoService, ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public String getMessage() {
        return "";
    }

    @Override
    public String process(String model) {
        JSONObject jsonObject = JSON.parseObject(model);
        ActionInfo actionInfo = jsonObject.getObject("actionInfo", ActionInfo.class);
//        String actionName = jsonObject.getString("actionName");
        StandProcess process = ActionManager.getBean(actionInfo);
        return process.handle(model);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
