package com.lefu.aop;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import net.sf.json.JSONObject;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.lefu.user.constant.LogConstant;
import com.lefu.user.constant.ResultConstant;
import com.lefu.user.exception.ServiceException;

/**
 * 
 * @author dan
 *
 */
@Aspect
@Order(5)
@Component
public class LogAspect {

	private Logger logger = LoggerFactory.getLogger(getClass());
	private Logger monitorLogger = LoggerFactory.getLogger(LogConstant.MONITOR);

	ThreadLocal<Long> startTime = new ThreadLocal<>();

	@Pointcut("execution(public * com.lefu.user.service..*.*(..)) ")
	public void servicePointcut() {
	}

	@Pointcut("execution(public * com.lefu.cust.web..*.*(..)) || execution(public * com.lefu.cust.enterprise.web..*.*(..))")
	public void controllerLog() {
	}

	@Before("controllerLog()")
	public void doBeforeController(JoinPoint joinPoint) throws Throwable {
		startTime.set(System.currentTimeMillis());
		logger.info("{}.{} ARGS: {}", joinPoint.getSignature().getDeclaringTypeName(), joinPoint
				.getSignature().getName(), Arrays.toString(joinPoint.getArgs()));
	}

	@Before("servicePointcut()")
	public void doBefore(JoinPoint joinPoint) throws Throwable {
		startTime.set(System.currentTimeMillis());
		logger.info("{}.{} ARGS: {}", joinPoint.getSignature().getDeclaringTypeName(), joinPoint
				.getSignature().getName(), Arrays.toString(joinPoint.getArgs()));
	}

	@AfterReturning(returning = "ret", pointcut = "servicePointcut()")
	public void doAfterReturning(JoinPoint joinPoint, Object ret) {
		monitorLogger.info("{} INFO SERVICE {} {} - end - {} -", getTimeStr(new Date()), joinPoint
				.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName(),
				System.currentTimeMillis() - startTime.get());
	}

	@Around("execution(public String com.lefu.user.service..*.*(..)) ")
	public Object doAround(ProceedingJoinPoint point) {
		JSONObject json = new JSONObject();
		try {
			return point.proceed(point.getArgs());
		} catch (IllegalArgumentException e) {
			json.put(ResultConstant.RESULT_CODE, ResultConstant.FAIL_CODE_98);
			json.put(ResultConstant.RESULT_MESSAGE, e.getMessage());
		} catch (ServiceException e) {
			json.put(ResultConstant.RESULT_CODE, e.getCode());
			json.put(ResultConstant.RESULT_MESSAGE, e.getMessage());
		} catch (Throwable e) {
			logger.error(e.getMessage(), e);
			json.put(ResultConstant.RESULT_CODE, ResultConstant.FAIL_CODE_99);
			json.put(ResultConstant.RESULT_MESSAGE, "调用接口失败" + point.getSignature().getName());
		}

		return json.toString();
	}

	public String getTimeStr(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(date);
	}
}
