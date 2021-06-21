package org.wangjj.practice.mytest;

/**
 * ClassName: CloneTest <br/>
 * Function: <br/>
 * Reason:  <br/>
 * date: 2021/5/19 下午2:58 <br/>
 *
 * @author wangjunjie
 * @since JDK 1.8
 */
public class CloneTest implements Cloneable{

    private int val;

    private InnerClass innerClass;

    public CloneTest(int val, InnerClass innerClass) {
        this.val = val;
        this.innerClass = innerClass;
    }

    public static class InnerClass implements Cloneable{

        private int val;

        public InnerClass(int val) {
            this.val = val;
        }

        public int getVal() {
            return val;
        }

        public void setVal(int val) {
            this.val = val;
        }

//        public String toString() {
//            return String.valueOf(val);
//        }

        @Override
        protected Object clone() throws CloneNotSupportedException {
            return super.clone();
        }
    }

    public InnerClass getInnerClass() {
        return innerClass;
    }

    public void setInnerClass(InnerClass innerClass) {
        this.innerClass = innerClass;
    }
    //    @Override
//    protected Object clone() throws CloneNotSupportedException {
//        return super.clone();
//    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        CloneTest test = (CloneTest)super.clone();
        test.innerClass = (InnerClass)test.getInnerClass().clone();
        return test;
    }

    public String toString() {
        return String.valueOf(val) + ":" + innerClass.toString();
    }

    public static void main(String[] args) {
        CloneTest test = new CloneTest(4, new CloneTest.InnerClass(3));
        System.out.println(test);
        try {
            System.out.println(test.clone());
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }
}
