package nsgsw1.netcare.repository.config;

import java.time.Duration;
import java.time.Instant;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
public class RespositoryInterceptor {

	private final static Logger logger = LoggerFactory
			.getLogger(RespositoryInterceptor.class);

	@Around("execution(* nsgsw1.netcare.repository..*Repository.*(..))")
	public Object logQueryTimes(ProceedingJoinPoint pjp) throws Throwable {
		Instant start = Instant.now();
		Object retVal = pjp.proceed();
		Instant end = Instant.now();
		Duration timeElapsed = Duration.between(start, end);
		long millis = timeElapsed.toMillis();
		String str = pjp.getTarget().toString();
		logger.info(str.substring(str.lastIndexOf(".") + 1, str
				.lastIndexOf("@"))
				+ " - "
				+ pjp.getSignature().getName()
				+ ": "
				+ millis + "ms");
		return retVal;
	}
}