package com.wangjj;

import org.apache.commons.io.IOUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;

import com.sk.skfw.SKFWUtils;
/**
 * ClassName: TestServlet <br/>
 * Function: <br/>
 * Reason:  <br/>
 * date: 2018/9/27 下午9:32 <br/>
 *
 * @author wangjunjie
 * @since JDK 1.8
 */
public class TestServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doPost(req, resp);
//        String data = "中国";
//        OutputStream outputStream = resp.getOutputStream();//获取OutputStream输出流
//        resp.setHeader("content-type", "text/html;charset=UTF-8");
//        outputStream.write(data.getBytes("utf-8"));
//        outputStream.flush();
//        outputStream.close();
        SKFWUtils fw = new SKFWUtils();
        String inputXml ="";
        try {
            inputXml = IOUtils.toString(req.getInputStream(), "gbk");
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        String result = fw.reqSK(inputXml, new HashMap());
        OutputStream outputStream = resp.getOutputStream();//获取OutputStream输出流
        resp.setHeader("content-type", "text/html;charset=gbk");
        outputStream.write(result.getBytes("gbk"));
        outputStream.flush();
        outputStream.close();
    }
}
