package com.hine.aspect;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by 齐海阳
 * Date: 2017/7/26
 * Time: 16:39
 */
@Aspect
@Component
public class HttpAspect {

    private final static Logger logger = LoggerFactory.getLogger(HttpAspect.class);

    @Pointcut("execution(public * com.hine.controller.GirlsController.*(..))")
    public void log(){
//        System.out.println("log log log log log log log log log log ");
        logger.info("log log log log log log log log log log ");
    }

    @Before("log()")
    public void doBefore(JoinPoint joinPoint){
//        System.out.println("1111111111111111  before before before before before before");
        logger.info("1111111111111111  before before before before before before");
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        //url
        logger.info("url={}",request.getRequestURL());
        //method
        logger.info("method={}",request.getMethod());
        //ip
        logger.info("ip={}",request.getRemoteAddr());
        //类方法
        logger.info("class_method={}",joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        //参数
        logger.info("args={}",joinPoint.getArgs());
    }

    @After("log()")
    public void doAfter(){
//        System.out.println("2222222222222222  after after after after after after after ");
        logger.info("2222222222222222  after after after after after after after ");
    }

    @AfterReturning(pointcut = "log()", returning = "object")
    public void doAfterReturning(Object object){
        logger.info("response={}",object.toString());
    }
}
