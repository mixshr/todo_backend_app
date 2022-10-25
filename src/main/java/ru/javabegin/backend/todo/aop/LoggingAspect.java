package ru.javabegin.backend.todo.aop;

import lombok.extern.java.Log;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Log
public class LoggingAspect {
    @Around("execution( * ru.javabegin.backend.todo.caontrollers..*(..)))")
    public Object profileControlMethods(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
        String className = methodSignature.getDeclaringType().getSimpleName();
        String methodName = methodSignature.getName();

        log.info("---------- Executing " + className + "." + methodName + " ----------- ");

        Object result = proceedingJoinPoint.proceed();

        return result;
    }
}