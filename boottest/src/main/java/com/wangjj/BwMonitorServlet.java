package com.wangjj;

import com.pki.biz.FpscExec;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.Map;
import java.util.Set;

/**
 * ClassName: TestServlet <br/>
 * Function: <br/>
 * Reason:  <br/>
 * date: 2018/9/27 下午9:32 <br/>
 *
 * @author wangjunjie
 * @since JDK 1.8
 */
public class BwMonitorServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Class fpscexecclass = FpscExec.class;
        Map fpscMap = null;
        try {
            Field field = fpscexecclass.getDeclaredField("threadMap");
            field.setAccessible(true);
            fpscMap = (Map)field.get(FpscExec.class);

        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        Set<String> jqbhs = fpscMap.keySet();
        StringBuilder sb = new StringBuilder();
        for (String jqbh : jqbhs) {
            sb.append(jqbh);
            sb.append("|");
        }
        OutputStream outputStream = resp.getOutputStream();//获取OutputStream输出流
        resp.setHeader("content-type", "text/html;charset=gbk");
        outputStream.write(sb.toString().getBytes());
        outputStream.flush();
        outputStream.close();
    }
}
