package com.epam.spring.core.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Created by Dmitrii_Topolnik on 8/8/2017.
 */
@Aspect
public class LoggingAspect {
    //private Logger LOG = Logger.getLogger(LoggingAspect.class.getName());
    private Map<Class<?>,Integer> counter = new HashMap<Class<?>, Integer>();
    @Pointcut("execution(* *.logEvent(..))")
    private void allLogEventMethods() {}

    @Pointcut("allLogEventMethods() && within(*.*File*Logger)")
    private void logEventInsideFileLoggers() {}

    @Before("allLogEventMethods()")
    public void logBefore(JoinPoint joinPoint) {
        System.out.println("BEFORE: " +
                joinPoint.getTarget().getClass().getSimpleName() + " " +
                joinPoint.getSignature().getName());
    }

    @AfterReturning(pointcut="allLogEventMethods()",
            returning="retVal")
    public void logAfter(Object retVal) {
        System.out.println("AFTER_RET: " + retVal);
    }

    @AfterThrowing(pointcut="allLogEventMethods()",
            throwing="ex")
    public void logAfterThrow(Throwable ex) {
        System.out.println("AFTER_THR: " + ex);
    }
}
