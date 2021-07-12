package org.wangjj.practice.serviceapi.domain;

import org.wangjj.practice.serviceapi.busiaction.ActionInfo;

public class BaseModel {
    private ActionInfo actionInfo;

    public ActionInfo getActionInfo() {
        return actionInfo;
    }

    public void setActionInfo(ActionInfo actionInfo) {
        this.actionInfo = actionInfo;
    }
}
