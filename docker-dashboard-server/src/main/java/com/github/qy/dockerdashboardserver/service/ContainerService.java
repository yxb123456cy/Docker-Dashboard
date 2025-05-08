package com.github.qy.dockerdashboardserver.service;

import com.github.dockerjava.api.command.InspectContainerResponse;
import com.github.dockerjava.api.command.TopContainerResponse;
import com.github.dockerjava.api.model.Container;
import com.github.qy.dockerdashboardserver.entity.Response;


import java.util.List;
import java.util.Map;

public interface ContainerService {

    //获取容器列表
    Response<List<Container>> list(boolean showAll);

    /**
     * 运行一个已经存在的容器
     *
     * @param containerId 容器ID
     */
    Response<Void> start(String containerId);

    /**
     * 停止容器
     *
     * @param containerId 停止的容器ID
     */
    Response<Void> stop(String containerId);

    /**
     * 暂停容器
     *
     * @apiNote 暂停指定容器(异步暂停)
     */
    Response<Void> pause(String containerId);

    /**
     * 取消暂停容器
     *
     * @param containerId 容器ID
     * @apiNote 取消暂停容器
     */
    Response<Void> unpause(String containerId);

    /**
     * 重启Docker容器
     * @param containerId 容器ID
     * @return Response<Void>
     */
    Response<Void> restart(String containerId );

    /**
     * 移除指定容器
     *
     * @param containerId 容器ID
     * @param param       辅助操作的参数
     */
    Response<Void> remove(String containerId, Map<String, String> param);

    /**
     * 容器重命名
     *
     * @param containerId 容器ID
     */
    Response<Void> rename(String containerId, String newName);

    /**
     * 查询容器的线程信息
     *
     * @param containerId 容器ID
     * @param psArgs      进程参数
     * @return TopContainerResponse
     */
    Response<TopContainerResponse> top(String containerId, String psArgs);

    /**
     * 查询容器详情
     *
     * @param containerId  容器ID
     * @return 容器详情数据;
     */
    Response<InspectContainerResponse> inspect(String containerId);
}
