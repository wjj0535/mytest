package com.pki.biz;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * ClassName: Fpsc <br/>
 * Function: <br/>
 * Reason:  <br/>
 * date: 2019/4/3 下午2:40 <br/>
 *
 * @author wangjunjie
 * @since JDK 1.8
 */
public class FpscExec {
    private static final Logger logger = LoggerFactory.getLogger(FpscExec.class);
    private static Map<String, String> threadMap;

    static {
        threadMap = new HashMap();
        threadMap.put("123", "321");
    }

    public static void main(String[] args) {
        Class fpscexecclass = FpscExec.class;
        try {
            Field field = fpscexecclass.getDeclaredField("threadMap");
            field.setAccessible(true);
            Map<String, String> obj = (Map<String, String>)field.get(FpscExec.class);
            Object val = obj.get("123");
            System.out.println(val);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }
}
