package com.quest.shoppingcart.aop.logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class loggingAspect {

	private Logger log = LoggerFactory.getLogger(getClass());

	@Before("execution(* com.quest.shoppingcart..*(..))")
	public void logMethodCall(JoinPoint joinPoint) {
		log.info("Entered inside the method - {} ", joinPoint);
	}

	@After("execution(* com.quest.shoppingcart..*(..))")
	public void logMethodExit(JoinPoint joinPoint) {
		log.info("Exited from the method - {} ", joinPoint);
	}

	@AfterThrowing(value = "execution(* com.quest.shoppingcart.service..*(..))", throwing = "ex")
	public void afterThrowingAdvice(JoinPoint joinPoint, Exception ex) {
		log.error("Exception in method - {}",joinPoint,  ex);
	}

	// Need to log payload and response body.
}
