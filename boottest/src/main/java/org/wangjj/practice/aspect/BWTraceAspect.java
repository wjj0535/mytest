package org.wangjj.practice.aspect;

import com.alibaba.fastjson.JSON;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * ClassName: TraceAspect <br/>
 * Function: <br/>
 * Reason:  <br/>
 * date: 2018/12/27 上午10:11 <br/>
 *
 * @author wangjunjie
 * @since JDK 1.8
 */
@Aspect
@Component
public class BWTraceAspect
{
    private final Logger logger = LoggerFactory.getLogger(BWTraceAspect.class);

    private static final LocalVariableTableParameterNameDiscoverer parameterNameDiscoverer = new LocalVariableTableParameterNameDiscoverer();

    private ThreadLocal<String> traceId = new ThreadLocal<>();

    @Pointcut(value = "@annotation(org.wangjj.practice.annotate.BWTrace)")
    public void annotationPointcut() {
    }

//    @Before("annotationPointcut()")
//    public void beforePointcut(JoinPoint joinPoint) {
//        MethodSignature methodSignature =  (MethodSignature) joinPoint.getSignature();
//        Method method = methodSignature.getMethod();
//        BWTrace annotation = method.getAnnotation(BWTrace.class);
//
//        //获取RequestAttributes
//        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
//        //从获取RequestAttributes中获取HttpServletRequest的信息
//        HttpServletRequest request = (HttpServletRequest) requestAttributes.resolveReference(RequestAttributes.REFERENCE_REQUEST);
//        //如果要获取Session信息的话，可以这样写：
//        //HttpSession session = (HttpSession) requestAttributes.resolveReference(RequestAttributes.REFERENCE_SESSION);
//        Enumeration<String> enumeration = request.getParameterNames();
//        Map<String,String> parameterMap = new HashMap<String,String>();
//        while (enumeration.hasMoreElements()){
//            String parameter = enumeration.nextElement();
//            parameterMap.put(parameter,request.getParameter(parameter));
//        }
//        String str = JSON.toJSONString(parameterMap);
//
//        logger.info("{}==> 请求的参数为：{}", method.getName(), str);
//
//        byte[] buffer = new byte[request.getContentLength()];
//        try {
//            ServletInputStream sis = request.getInputStream();
//            logger.info("可读：{}", sis.available());
//            sis.read(buffer, 0, request.getContentLength());
//
//            String body = new String(buffer, request.getCharacterEncoding());
//            logger.info("body数据：{}", body);
//
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//    }

//    @After("annotationPointcut()")
//    public void afterPointcut(JoinPoint joinPoint) {
//        MethodSignature methodSignature =  (MethodSignature) joinPoint.getSignature();
//        Method method = methodSignature.getMethod();
//    }
//
    @AfterReturning(returning = "ret", pointcut = "annotationPointcut()")
    public void afterExec(JoinPoint joinPoint, Object ret) {
        MethodSignature methodSignature =  (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        logger.info("{}==> 返回值为：{}", method.getName(), JSON.toJSONString(ret));
    }

    @Around("annotationPointcut()")
    public void around(ProceedingJoinPoint joinPoint){
        long start = System.currentTimeMillis();
        try {
            //判断controller注解和其他注解
            Class objClass = joinPoint.getTarget().getClass();
            Annotation[] annotations = objClass.getAnnotations();

            if(null != objClass.getAnnotation(Controller.class) || null != objClass.getAnnotation(RestController.class)) {
                traceId.set(UUID.randomUUID().toString());
                logger.info("[traceId:{}]controller入口", traceId.get());
            } else {
                logger.info("[traceId:{}]调用函数钱", traceId.get());
            }

            MethodSignature methodSignature =  (MethodSignature) joinPoint.getSignature();
            Method method = methodSignature.getMethod();

            String[] paraNames = parameterNameDiscoverer.getParameterNames(method);

            Parameter[] parameters = method.getParameters();
            Object[] args = joinPoint.getArgs();
            if(args.length == 1 && args[0] instanceof ServletRequest) {
                BWServletRequestWrapper request = new BWServletRequestWrapper((HttpServletRequest)args[0]);
                args[0] = request;
                String body = new String(request.getBody(), request.getCharacterEncoding());
                logger.info("{}==> 请求的body数据：{}", method.getName(), body);
            } else {
                Map<String, Object> mapArgs = new HashMap<>();
                //获取RequestAttributes
                RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
                //从获取RequestAttributes中获取HttpServletRequest的信息
                HttpServletRequest request = (HttpServletRequest) requestAttributes.resolveReference(RequestAttributes.REFERENCE_REQUEST);
                for(int i=0; i<args.length; i++) {
                    Object objArg = args[i];
                    if(!(objArg instanceof ServletRequest)) {
                        mapArgs.put(paraNames[i], objArg);
                    }
                }
                logger.info("{}==> 请求的参数为：{}", method.getName(), JSON.toJSONString(mapArgs));
            }
            joinPoint.proceed(args);

        } catch (Throwable e) {
            logger.error("日志拦截异常", e);
        }
    }
}
