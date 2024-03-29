package com.bdd.rest.integration.config;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.CodeSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Slf4j
@Aspect
@Component
public class LoggingAspect {
    private static final String EXECUTION_EXCLUDING_GET = " !execution( public * com.bdd.rest..get*()) ";
    private static final String EXECUTION_EXCLUDING_SET = " !execution( public * com.bdd.rest..set*()) ";
    private static final String EXECUTION_EXCLUDING_CONFIG = " !execution( * com.bdd.rest..*config*..*(..)) ";
    private static final String EXECUTION_EXCLUDING_REPO = " !execution( * com.bdd.rest..*repo*..*(..)) ";
    private static final String AMP = " && ";
    private static final String EXCLUSION = EXECUTION_EXCLUDING_GET + AMP + EXECUTION_EXCLUDING_SET + AMP + EXECUTION_EXCLUDING_CONFIG + AMP + EXECUTION_EXCLUDING_REPO + AMP;
    private static final String EXECUTION_COM_ADIDAS_SAM_MICROSERVICE = EXCLUSION + "execution(* com.bdd.rest..*(..))";

    @Before(EXECUTION_COM_ADIDAS_SAM_MICROSERVICE)
    @Order(1)
    public void logBefore(JoinPoint joinPoint) {

        Class<?> aClass = joinPoint.getTarget().getClass();

        CodeSignature codeSignature = (CodeSignature) joinPoint.getSignature();
        log.debug("\n ======================================================================= ");
        log.debug("class : " + aClass.getCanonicalName());
        log.debug("Method : " + codeSignature.getName());
        log.debug("with Parameter names : " + Arrays.toString(codeSignature.getParameterNames()));
        log.debug("with values : " + Arrays.toString(joinPoint.getArgs()));
    }

    @AfterReturning(EXECUTION_COM_ADIDAS_SAM_MICROSERVICE)
    @Order(1)
    public void logAfter(JoinPoint joinPoint) {
        log.debug("Method execution finished : " + joinPoint.getSignature().getName());
    }

}