package org.wangjj.practice.service2.service.middleground;

import org.springframework.beans.factory.BeanNameAware;
import org.wangjj.practice.service2.annotation.middleground.MdAction;

public class BaseService implements BeanNameAware {
    @Override
    public void setBeanName(String s) {
        Class actionClass = this.getClass();
        MdAction action = (MdAction)actionClass.getAnnotation(MdAction.class);
        if (null != action) {
            s = String.format("%s_%s", action.code(), action.version());
        }
        return;
    }
}
