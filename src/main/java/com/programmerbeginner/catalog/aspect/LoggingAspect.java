package com.programmerbeginner.catalog.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@Aspect
public class LoggingAspect {

    @Pointcut("execution(* com.programmerbeginner.catalog.web.*.*(..))")
    private void restApi() {}

    @Pointcut("within(com.programmerbeginner.catalog.web.*)")
    private void withinPointcutExample() {}

    @Pointcut("args(com.programmerbeginner.catalog.dto.PublisherCreateRequestDto)")
    private void argsPointcutExample() {}

    @Pointcut("@args(com.programmerbeginner.catalog.annotasi.LogThisArg)")
    private void argsAnotasiPointcutExample() {}

    @Pointcut("@annotation(com.programmerbeginner.catalog.annotasi.LogThisMethod)")
    private void annotationPointcutExample() {}

    @Before("annotationPointcutExample()")
    public void beforeExecuteLogging() {
        log.info("This log message is from an aspect before the method is executed");
    }

    @After("annotationPointcutExample()")
    public void afterExecuteLogging() {
        log.info("This log message is from an aspect after the method is executed");
    }

    @AfterReturning(pointcut = "annotationPointcutExample()", returning = "result")
    public void afterReturnExecuteLogging(Object result) {
        log.info("This log message is from an aspect after the method returns");
    }

    @AfterThrowing(pointcut = "annotationPointcutExample()", throwing = "exception")
    public void afterThrowingExecuteLogging(Exception exception) {
        log.info("This log message is from an aspect after an error is thrown");
    }

    @Around("restApi()")
    public Object processingTimeLogging(ProceedingJoinPoint joinPoint) throws Throwable {
        StopWatch stopWatch = new StopWatch();

        try {
            log.info("************start {}.{}", joinPoint.getTarget().getClass().getName(), joinPoint.getSignature().getName());
            stopWatch.start();
            return joinPoint.proceed();
        } finally {
            stopWatch.stop();
            log.info("********finish {}.{} execution time = {} ms", joinPoint.getTarget().getClass().getName(), joinPoint.getSignature().getName(),
                    stopWatch.getTotalTimeMillis());
        }
    }
}
