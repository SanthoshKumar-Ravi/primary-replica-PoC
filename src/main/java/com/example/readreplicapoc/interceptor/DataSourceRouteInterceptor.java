package com.example.readreplicapoc.interceptor;

import com.example.readreplicapoc.router.DataSourceRouter;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Aspect
@Component
@Order(0)
public class DataSourceRouteInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(DataSourceRouteInterceptor.class);
    @Around("@annotation(transactional)")
    public Object proceed(ProceedingJoinPoint proceedingJoinPoint, Transactional transactional) throws Throwable {
        try {
            if (transactional.readOnly()) {
                DataSourceRouter.setReplicaRoute();
                logger.info("Routing database call to the read replica");
            }
            return proceedingJoinPoint.proceed();
        } finally {
            DataSourceRouter.clearReplicaRoute();
        }
    }
}
