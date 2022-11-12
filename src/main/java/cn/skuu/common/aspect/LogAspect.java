package cn.skuu.common.aspect;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.fasterxml.jackson.databind.ObjectMapper;
import cn.skuu.pojo.dto.SysLogDTO;
import cn.skuu.util.IpUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Objects;

/**
 * dcx
 *
 * @author dcx
 * @date 2022/11/6 17:05
 **/
@Order(1)
@Slf4j
@Aspect
@Component
public class LogAspect {

    @Autowired
    private ObjectMapper objectMapper;

    // 定义切点Pointcut
    @Pointcut("execution(* cn.skuu.controller..*.*(..))")
    public void excudeService() {
    }

    @Around("excudeService()")
    public Object doAround(ProceedingJoinPoint point) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) Objects
                .requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();

        // 有些入参 JSON.toJSONString 会报错 catch一下
        SysLogDTO sysLogDTO = new SysLogDTO();
        try {
            sysLogDTO.setUuid(IdWorker.get32UUID());
            sysLogDTO.setClassName(point.getTarget().getClass().getName());
            sysLogDTO.setMethodName(point.getSignature().getName());
            sysLogDTO.setRemoteAddr(IpUtils.getIpAddr(request));
            sysLogDTO.setRequestUri(request.getRequestURI());
            sysLogDTO.setOrigin(request.getHeader(HttpHeaders.ORIGIN));
            sysLogDTO.setReferer(request.getHeader(HttpHeaders.REFERER));
            sysLogDTO.setUserAgent(request.getHeader(HttpHeaders.USER_AGENT));
            sysLogDTO.setParameterMapParams(objectMapper.writeValueAsString(request.getParameterMap()));
            sysLogDTO.setArraysGetArgsParams(Arrays.toString(point.getArgs()));
            sysLogDTO.setJsonGetArgsParams(objectMapper.writeValueAsString(point.getArgs()));
        } catch (Exception e) {
            log.error("param error,msg:", e);
        }
        log.info("request:{}", sysLogDTO);
        Object obj;
        try {
            obj = point.proceed();
            sysLogDTO.setResponse(objectMapper.writeValueAsString(obj));
            log.info("response:{}", sysLogDTO);
        } catch (Throwable e) {
            sysLogDTO.setResponse(e.getMessage());
            throw e;
        }
        return obj;
    }

}
