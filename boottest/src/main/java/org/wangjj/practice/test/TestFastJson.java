package org.wangjj.practice.test;

import com.alibaba.fastjson.JSON;
import org.wangjj.practice.model.BWJsonResult;

/**
 * ClassName: TestFastJson <br/>
 * Function: <br/>
 * Reason:  <br/>
 * date: 2018/12/12 下午3:32 <br/>
 *
 * @author wangjunjie
 * @since JDK 1.8
 */
public class TestFastJson {
    public static class Test {
        private String ssss;

        public String getSsss() {
            return ssss;
        }

        public void setSsss(String ssss) {
            this.ssss = ssss;
        }
    }
    public static void main(String[] args) {
        Double d = 1000023000000003.93;
        System.out.println(String.valueOf(d));
//        BWJsonResult<Test> bw = new BWJsonResult<Test>();
//        Test test = new Test();
//        test.ssss = "123";
//        bw.getData().add(test);
//        String s1 = JSON.toJSONString(bw);
//        System.out.println(s1);
//
//        BWJsonResult bw2 = JSON.parseObject(s1, BWJsonResult.class);
//        System.out.println(JSON.toJSONString(bw2));
    }
}
