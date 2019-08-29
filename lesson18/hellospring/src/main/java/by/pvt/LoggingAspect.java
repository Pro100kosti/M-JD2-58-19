package by.pvt;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    @Before("execution(public * *(..))")
    public void log(JoinPoint joinPoint) {
        System.out.println("LoggingAspect.log() "
                + joinPoint.getTarget()
                + "."
                + joinPoint.getSignature().getName());
    }

}
