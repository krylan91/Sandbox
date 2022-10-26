package me.aakrylov.sandbox.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ServiceLogger {

    private static final Logger logger = LoggerFactory.getLogger(ServiceLogger.class);

    @Pointcut("@annotation(me.aakrylov.sandbox.annotation.Loggable)")
    public void loggableExecute() { /*method-based pointcut*/ }

//    @Around("loggableExecute()")
//    public Object loggableCall(ProceedingJoinPoint joinPoint) {
//        logger.info("Call on {}", joinPoint.getSignature().getName());
//        try {
//            Object retval = joinPoint.proceed();
//            logger.info("Finished {}", joinPoint.getSignature().getName());
//            return retval;
//        } catch (Throwable e) {
//            logger.error("Error executing {}", joinPoint.getSignature().getName(), e);
//            return null;
//        }
//    }

    @Before("loggableExecute()")
    public void loggableCall2(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getDeclaringType() + "." + joinPoint.getSignature().getName();
        logger.info("Call on {}", methodName);
    }
}
