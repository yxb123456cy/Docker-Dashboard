package com.github.qy.dockerdashboardserver.controller;


import com.github.qy.dockerdashboardserver.entity.Response;
import io.micrometer.core.instrument.config.validate.ValidationException;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 用于接口参数校验处理的控制器
 */
@Slf4j
@RestControllerAdvice
public class ValidationController {

    private void logE(Exception e) {
        log.warn("Resolved [{}: {}]", e.getClass().getName(), e.getMessage());
    }

    /**
     * 与SpringBoot保持一致，校验不通过打印警告信息，而不是直接抛出异常
     * @return 校验结果
     */
    @ExceptionHandler(ValidationException.class)
    public Response<Void> validateError(ValidationException exception) {
        logE(exception);
        return Response.fail("请求参数有误!");
    }

    // 全局异常拦截
    @ExceptionHandler
    public Response<Void> handlerException(Exception e) {
        logE(e);
        return Response.fail(e.getMessage());
    }
}
