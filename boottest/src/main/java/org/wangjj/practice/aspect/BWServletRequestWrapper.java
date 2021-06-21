package org.wangjj.practice.aspect;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * ClassName: BWServletRequestWrapper <br/>
 * Function: <br/>
 * Reason:  <br/>
 * date: 2018/12/27 下午5:32 <br/>
 *
 * @author wangjunjie
 * @since JDK 1.8
 */
public class BWServletRequestWrapper extends HttpServletRequestWrapper {

    private final byte[] body;

    public BWServletRequestWrapper(HttpServletRequest request) {
        super(request);
        ServletInputStream sis = null;
        body = new byte[request.getContentLength()];
        try {
            sis = request.getInputStream();
            sis.read(body, 0, request.getContentLength());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public byte[] getBody() {
        return body;
    }

    @Override
    public BufferedReader getReader() {
        return new BufferedReader(new InputStreamReader(getInputStream()));
    }

    @Override
    public ServletInputStream getInputStream() {
        final ByteArrayInputStream bais = new ByteArrayInputStream(body);
        return new ServletInputStream() {

            @Override
            public boolean isFinished() {
                return false;
            }

            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setReadListener(ReadListener readListener) {

            }

            @Override
            public int read() {
                return bais.read();
            }
        };
    }
}
