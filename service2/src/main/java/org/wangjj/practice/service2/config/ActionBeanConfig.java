package org.wangjj.practice.service2.config;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.wangjj.practice.service2.annotation.middleground.MdAction;
import org.wangjj.practice.service2.service.ActionManager;

import java.util.Map;

@Component
public class ActionBeanConfig implements ApplicationListener<ContextRefreshedEvent> {
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        ConfigurableApplicationContext context = (ConfigurableApplicationContext) contextRefreshedEvent.getApplicationContext();
        DefaultListableBeanFactory factory = (DefaultListableBeanFactory) context.getBeanFactory();
        // 控制器
        Map<String, Object> beans = factory.getBeansWithAnnotation(MdAction.class);

        beans.forEach((k, v)->{
            Class actionClass = v.getClass();
            MdAction action = (MdAction)actionClass.getAnnotation(MdAction.class);
            if (null != action) {
                ActionManager.addAction(action.code()+"_"+action.version(), actionClass);
            }
        });
    }
}
