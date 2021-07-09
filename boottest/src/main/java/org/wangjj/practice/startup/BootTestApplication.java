package org.wangjj.practice.startup;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.LinkedHashSet;

/**
 * ClassName: BootTestApplication <br/>
 * Function: <br/>
 * Reason:  <br/>
 * date: 2018/8/7 下午5:40 <br/>
 *
 * @author wangjunjie
 * @since JDK test.8
 */
@SpringBootApplication
@ComponentScan(basePackages = "org.wangjj.practice.*")
@MapperScan(basePackages = "org.wangjj.practice.*")
public class BootTestApplication {

    public static void main(String[] args) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, InvalidKeySpecException, UnsupportedEncodingException, BadPaddingException, IllegalBlockSizeException
    {
//        URL url1 = BootTestApplication.class.getResource("xxxs");
//        URL url2 = BootTestApplication.class.getResource("/test");
//        URL url3 = BootTestApplication.class.getClassLoader().getResource("test");
//        URL url4 = BootTestApplication.class.getClassLoader().getResource("/test");
//        String[] ssd = "".split(",");
//        LinkedHashSet<String> ss = new LinkedHashSet<>();
//        ss.add("test");
        SpringApplication.run(BootTestApplication.class, args);



//        try {
//            URL url = new URL("http://192.168.6.53:8081/SKServer/test");
//            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//            conn.setDoOutput(true);
//            conn.setDoInput(true);
//            //POST请求
//            conn.setRequestMethod("POST");
//            OutputStream wr = conn.getOutputStream();
//            //读字节流
//            String data = "<?xml version=\"test.0\" encoding=\"gbk\"?>" +
//                    "<business id=\"90004\" comment=\"税控服务器查询\">" +
//                    "<body>" +
//                    "<type>fwqxx</type>" +
//                    "<nsrsbh>91500000747150329A</nsrsbh>" +
//                    "</body>" +
//                    "</business>";
//            System.out.println(data.length());
//            InputStream inputStream = new ByteArrayInputStream(data.getBytes("gbk"));
//            byte[] content = IOUtils.toByteArray(inputStream);
//            wr.write(content);
//            wr.flush();
//            System.out.println("result = "+ IOUtils.toString(conn.getInputStream(), "gbk"));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }


        //skserver解密密码
//        String password = "3DES{46la9eGyU9rEZcoBcn7TXhNB+XHgxmXXEwC9CywseJk=}";
//        Cipher cipher = Cipher.getInstance("DES");
//        DESKeySpec dks = new DESKeySpec("skfwqglxt1234567890X".getBytes());
//        Key kg = SecretKeyFactory.getInstance("DES").generateSecret(dks);
//        if (password.contains("3DES{")) {
//            cipher.init(2, kg);
//            String yw = password.substring(password.indexOf("3DES{") + 5, password.lastIndexOf("}"));
//            byte[] ba = cipher.doFinal(cipher.doFinal(cipher.doFinal(Base64.getDecoder().decode(yw.getBytes("UTF-8")))));
//            System.out.println(new String(ba, "UTF-8"));
//        }
    }
}
