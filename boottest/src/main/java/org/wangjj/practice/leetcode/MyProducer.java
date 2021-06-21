package org.wangjj.practice.leetcode;

/**
 * ClassName: MyProducer <br/>
 * Function: <br/>
 * Reason:  <br/>
 * date: 2021/3/5 下午2:10 <br/>
 *
 * @author wangjunjie
 * @since JDK 1.8
 */
public class MyProducer {
    private MyBlockingQueue queue;

    public MyProducer(MyBlockingQueue queue) {
        this.queue = queue;
    }

    public void addObj(String str) {
        queue.put(str);
        System.out.println("put str="+str);
    }

    public static void main(String[] args) {
        MyBlockingQueue queue = new MyBlockingQueue(2);
        MyConsumer consumer = new MyConsumer(queue);
        new Thread(consumer).start();
        MyProducer producer = new MyProducer(queue);
        for (int i=0; i<10; i++) {
            producer.addObj(String.valueOf(i));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
