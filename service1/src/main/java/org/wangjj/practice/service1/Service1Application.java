package org.wangjj.practice.service1;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@EnableDubbo
public class Service1Application
{
    public static void main( String[] args )
    {
        SpringApplication.run(Service1Application.class, args);
    }
}
