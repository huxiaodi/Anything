package com.test.framework.aspect;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by hugo on 2017/10/15.
 */
@Aspect
@Component
public class HttpAspect {

    private final static Logger logger = LogManager.getLogger(HttpAspect.class);

    @Pointcut("execution(public * com.test.manage.*.*(..))")
    public void log() {
    }

    @Before("log()")
    public void logBefore(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        logger.info("url={}", request.getRequestURL());
        logger.info("method={}", request.getMethod());
        logger.info("ip={}", request.getRemoteAddr());
        logger.info("class_method={}", joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        logger.info("args={}", joinPoint.getArgs());

    }

    @After("log()")
    public void logAfter() {
        logger.info("logAfter");

    }

    @AfterReturning(returning = "object", pointcut = "log()")
    public void logAfterRetruning(Object object) {
        logger.info(object.toString());
    }
}
