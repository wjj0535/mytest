package org.wangjj.practice.service2.service.supplier;

public interface StandProcess {
    String preHandle(String data);
    String handle(String data);
    String postHandle(String data);
}
