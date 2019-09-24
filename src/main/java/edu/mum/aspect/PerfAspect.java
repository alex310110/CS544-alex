package edu.mum.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class PerfAspect {
	@Pointcut("execution(* edu.mum.service.BuyerService.findAllPerf(..))")
	public void pointCut() {
	}

	@Around("pointCut()")
	public Object around(ProceedingJoinPoint jp) throws Throwable {
		long startTime = System.currentTimeMillis();
		Object ret = jp.proceed();
		long endTime = System.currentTimeMillis();
		System.out.println(jp.getSignature() +
				" took " + (endTime - startTime) + " ms");
		return ret;
	}
}
