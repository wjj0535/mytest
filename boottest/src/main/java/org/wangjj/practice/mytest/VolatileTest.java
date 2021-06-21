package org.wangjj.practice.mytest;

/**
 * ClassName: VolatileTest <br/>
 * Function: <br/>
 * Reason:  <br/>
 * date: 2021/4/7 上午10:08 <br/>
 *
 * @author https://blog.csdn.net/wjj0535
 * @since JDK 1.8
 */
public class VolatileTest {
    private static volatile int testInt = 0;
//    private static  int testInt = 0;
    public static void main(String[] args) {
        new Thread(){
            @Override
            public void run() {
                while(testInt+0 == 0) {

                }
                System.out.println("A Thread: return" + System.currentTimeMillis());
            }
        }.start();
        new Thread(){
            @Override
            public void run() {
                while(testInt+0 == 0) {

                }
                System.out.println("B Thread: return" + System.currentTimeMillis());
            }
        }.start();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        testInt++;
    }
}
