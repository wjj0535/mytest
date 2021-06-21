package org.wangjj.practice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wangjj.practice.annotate.BWTrace;
import org.wangjj.practice.service.MyTestService;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * ClassName: MocMvcController <br/>
 * Function: <br/>
 * Reason:  <br/>
 * date: 2018/8/7 下午5:46 <br/>
 *
 * @author wangjunjie
 * @since JDK 1.8
 */
@RestController
@RequestMapping(value = "/test")
public class MocMvcController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MyTestService myTestService;

    @BWTrace
    @PostMapping(value = "/add")
    public String add(HttpServletRequest request) {
        //logger.info(msg);
        byte[] buffer = new byte[request.getContentLength()];
        InputStreamReader br = null;
        try {
            ServletInputStream sis = request.getInputStream();

            sis.read(buffer, 0, request.getContentLength());

            String body = new String(buffer, request.getCharacterEncoding());
            logger.info("controller body数据：{}, {}", body, sis.available());
            myTestService.test("13");
            logger.info("调用service:");


        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //logger.info(str);
        return "{\"test\":33}";
    }

    @PostMapping(value = "/sub")
    public String sub(@RequestBody String msg) {
        logger.info(msg);
        return "{\"test\":22}";
    }
}
