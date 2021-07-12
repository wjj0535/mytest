package org.wangjj.practice.service2.service;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.wangjj.practice.service2.service.middleground.StandProcess;
import org.wangjj.practice.serviceapi.busiaction.ActionInfo;

import java.util.HashMap;
import java.util.Map;

@Component
public class ActionManager implements ApplicationContextAware {

    private static ApplicationContext applicationContext ;

    private static Map<String, Class> allActionList = new HashMap<>();

    public Map<String, Class> getAllActionList() {
        return allActionList;
    }

    public void setAllActionList(Map<String, Class> allActionList) {
        this.allActionList = allActionList;
    }

    public static void addAction(String name, Class aclass) {
        allActionList.put(name, aclass);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public static StandProcess getBean(ActionInfo actionInfo) {
        return (StandProcess)applicationContext.getBean(allActionList.get(String.format("%s_%s", actionInfo.getAction(), actionInfo.getVersion())));
    }
}
