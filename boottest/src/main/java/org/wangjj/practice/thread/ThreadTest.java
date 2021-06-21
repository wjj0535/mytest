package org.wangjj.practice.thread;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ClassName: ThreadTest <br/>
 * Function: <br/>
 * Reason:  <br/>
 * date: 2020/10/30 下午9:50 <br/>
 *
 * @author wangjunjie
 * @since JDK test.8
 */
public class ThreadTest implements Runnable{

    private int id = 0;

    private static int total = 0;

    private static AtomicInteger atomicInteger = new AtomicInteger(0);

    public static void setAtomicInteger(AtomicInteger atomicInteger) {
        ThreadTest.atomicInteger = atomicInteger;
    }

    public static AtomicInteger getAtomicInteger() {
        return atomicInteger;
    }

    private ReentrantLock lock;

    private List<Condition> conditionList ;

    private List<String> printData ;

    public ThreadTest() {
        this.id = total++;
    }

    public ThreadTest(ReentrantLock lock, List<String> printData, List<Condition> conditionList) {
        this.lock = lock;
        this.printData = printData;
        this.conditionList = conditionList;
        this.id = total++;
    }

//    @Override
//    public void run() {
//        for (;;) {
//            if (atomicInteger.intValue()%total == id) {
//                System.out.println("thread-id="+Thread.currentThread().getId()+":"+atomicInteger.intValue());
//                atomicInteger.getAndIncrement();
//                try {
//                    Thread.sleep(500);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            } else {
//                try {
//                    Thread.sleep(1010);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//
//    }
    @Override
    public void run() {
        int dataSize = printData.size();
        int index = 0;
        for (;;) {
            try {
                lock.lock();
                System.out.println("thread-id="+Thread.currentThread().getId()+":"+printData.get(index));
                Thread.sleep(500);
                for (;;) {

                }
//                Condition condition = conditionList.get(this.id);
//                Condition nextCondition = conditionList.get((this.id+test)%total);
//                nextCondition.signal();
//                condition.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
            index++;
            if (index == dataSize) {
                index = 0;
            }
        }

    }

    public static void main(String[] args) {
        List<String> dataNum = Arrays.asList(new String[]{"test","2","3","4","5","6"});
        List<String> dataChar = Arrays.asList(new String[]{"A","B","C","D","E","F"});
        ReentrantLock lock = new ReentrantLock();
        List<Condition> conditions = new ArrayList<>();
        conditions.add(lock.newCondition());
        conditions.add(lock.newCondition());

        new Thread(new ThreadTest(lock, dataNum, conditions)).start();
        new Thread(new ThreadTest(lock, dataChar, conditions)).start();


    }
}
