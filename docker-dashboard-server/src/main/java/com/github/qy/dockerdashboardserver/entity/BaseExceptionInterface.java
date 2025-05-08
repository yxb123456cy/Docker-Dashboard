package com.github.qy.dockerdashboardserver.entity;

public interface BaseExceptionInterface {
    // 获取异常码
    String getErrorCode();

    // 获取异常信息
    String getErrorMessage();
}
