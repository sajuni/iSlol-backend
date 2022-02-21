package com.insung.lol.common.aop;

import com.insung.lol.common.annotation.TraceLog;
import com.insung.lol.common.logtrace.LogTrace;
import com.insung.lol.common.logtrace.TraceStatus;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Slf4j
@Aspect
public class LogTraceAspect {

    private final LogTrace logTrace;

    public LogTraceAspect(LogTrace logTrace) {
        this.logTrace = logTrace;
    }
//    @Around("execution(* hello.aop..*(..)) && @target(hello.aop.member.annotation.ClassAop)")
//    @Around("bean(*Controller) || bean(*ServiceImpl) || bean(*Repository)")
//    @Around("@annotation(trace)")
    @Around("execution(* com.insung.lol..*(..)) && @within(com.insung.lol.common.annotation.TraceLog)")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        TraceStatus status = null;
        try {
            String message = joinPoint.getSignature().toShortString();
            status = logTrace.begin(message);
            //로직 호출
            Object result = joinPoint.proceed();

            logTrace.end(status);
            return result;
        } catch (Exception e) {
            logTrace.exception(status, e);
            throw e;
        }

    }


}
