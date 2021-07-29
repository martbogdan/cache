package com.example.cacher.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class RunSafeAspect {

    @Pointcut("@annotation(RunSafe)")
    public void callAtComponent() {}

    @AfterThrowing(pointcut = "callAtComponent()", throwing = "e")
    public void afterThrowingCallAt(Throwable e) {
        log.error("EEERRRROOORRR " + e.getMessage());
    }

    @Around("callAtComponent()")
    public Object aroundCallAt(ProceedingJoinPoint pjp)  {
        log.info("!!!!!!!! Fetching from DB !!!!!!!!!");
        try {
            return pjp.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return null;
    }
}
