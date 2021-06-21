package org.wangjj.practice.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.wangjj.practice.annotate.BWTrace;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * ClassName: MyTestService <br/>
 * Function: <br/>
 * Reason:  <br/>
 * date: 2018/12/28 下午4:02 <br/>
 *
 * @author wangjunjie
 * @since JDK test.8
 */
@Service
public class MyTestService implements BeanNameAware, BeanFactoryAware,
        ApplicationContextAware, BeanPostProcessor, InitializingBean, DisposableBean {
    //
    private final Logger logger = LoggerFactory.getLogger(MyTestService.class);

    private HashSet<String> set = new HashSet<>();

    public MyTestService() {
        System.out.println("construct method");
    }

    static {
        System.out.println("static");
    }

    @Autowired
    @Lazy
    private MPTestService testService;

    public void setTestService(MPTestService testService) {
        this.testService = testService;
    }

    @PostConstruct
    public void init() {
        System.out.println("PostConstruct");
    }

    @BWTrace
    public void test(String param) {
        logger.info("exec result : " + param);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("setBeanFactory");
    }

    @Override
    public void setBeanName(String name) {
        System.out.println("setBeanName:" + name);
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("destroy");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("afterPropertiesSet");
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        //System.out.println("postProcessBeforeInitialization");
        if ("MyTestService".equals(beanName)) {
            System.out.println("postProcessBeforeInitialization: "+beanName);
        }
        if (!set.add(beanName)) {
            System.out.println("");
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
//        System.out.println("postProcessAfterInitialization");
        return bean;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("setApplicationContext");
    }
}
