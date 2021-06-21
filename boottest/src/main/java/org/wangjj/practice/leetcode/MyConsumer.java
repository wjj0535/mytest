package org.wangjj.practice.leetcode;

/**
 * ClassName: MyConsumer <br/>
 * Function: <br/>
 * Reason:  <br/>
 * date: 2021/3/5 下午2:11 <br/>
 *
 * @author wangjunjie
 * @since JDK 1.8
 */
public class MyConsumer implements Runnable {
    private MyBlockingQueue myBlockingQueue;

    public MyConsumer(MyBlockingQueue myBlockingQueue) {
        this.myBlockingQueue = myBlockingQueue;
    }

    @Override
    public void run() {
        while (true) {
            String str = (String)myBlockingQueue.offer();
            System.out.println("get str="+str);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
