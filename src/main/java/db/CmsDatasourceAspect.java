package db;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

 

//@Aspect
//@Component
public class CmsDatasourceAspect {
    
    //@Around("execution(* dao.*.getSeq(..))")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        MethodSignature joinPointObject = (MethodSignature) pjp.getSignature();
        Method method = joinPointObject.getMethod();
        
        System.out.println(method.getName() + method.isAnnotationPresent(RoutingDatasource.class));
        
        if (method.isAnnotationPresent(RoutingDatasource.class)) {
            RoutingDatasource routingDs = method.getAnnotation(RoutingDatasource.class);
            ThreadLocalKeyRoutingDataSource._DATA_SOURCE_KEY.set(routingDs.value());
        }

        try {
            return pjp.proceed();
        } catch (Exception e) {
            throw e;
        } finally {
            ThreadLocalKeyRoutingDataSource._DATA_SOURCE_KEY.set(null);
        }

    }

}
