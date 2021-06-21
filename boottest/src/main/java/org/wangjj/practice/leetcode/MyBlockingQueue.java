package org.wangjj.practice.leetcode;

import java.util.*;

/**
 * ClassName: MyBlockingQueue <br/>
 * Function: <br/>
 * Reason:  <br/>
 * date: 2021/3/5 下午2:09 <br/>
 *
 * @author wangjunjie
 * @since JDK 1.8
 */
public class MyBlockingQueue {

    private Object lock = new Object();

    private Object isFullLock = new Object();

    private int cap = 0;

    private LinkedList<Object> queue = new LinkedList<>();

    public MyBlockingQueue(int cap) {
        this.cap = cap;
    }

    public void put(Object obj) {
        synchronized (isFullLock) {
            if (queue.size() >= cap) {
                try {
                    isFullLock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            queue.push(obj);
        }
        synchronized (lock) {
            lock.notifyAll();
        }
    }

    public Object offer() {
        Object ret = null;
        synchronized (lock) {
            try {
                if (queue.isEmpty()) {
                    lock.wait();
                }
                ret = queue.pop();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        synchronized (isFullLock) {
            if (queue.size() < cap) {
                isFullLock.notifyAll();
            }
        }
        return ret;
    }
}
