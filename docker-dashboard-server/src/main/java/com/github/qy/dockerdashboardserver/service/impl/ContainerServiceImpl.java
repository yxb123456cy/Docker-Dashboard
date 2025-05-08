package com.github.qy.dockerdashboardserver.service.impl;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.InspectContainerResponse;
import com.github.dockerjava.api.command.TopContainerResponse;
import com.github.dockerjava.api.model.Container;
import com.github.qy.dockerdashboardserver.entity.Response;
import com.github.qy.dockerdashboardserver.service.ContainerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class ContainerServiceImpl implements ContainerService {
    private final DockerClient dockerClient;

    /**
     * 获取Docker容器列表
     * @param showAll 是否获取全部
     * @return 容器列表;
     */
    @Override
    public Response<List<Container>> list(boolean showAll) {
        List<Container> containerList = dockerClient.listContainersCmd()
                .withShowAll(showAll)
                .exec();
        return Response.success(containerList);
    }

    /**
     * 启动一个存在的容器
     * @param containerId 容器ID
     * @return Response<Void>
     */
    @Override
    public Response<Void> start(String containerId) {
        dockerClient.startContainerCmd(containerId)
                .exec();
        //后续补上记录日志操作;
        log.info("启动容器,ContainerID:{}", containerId);
        return Response.success();
    }


    /**
     * 停止容器
     * @param containerId 停止的容器ID
     * @return Response<Void>
     */
    @Override
    public Response<Void> stop(String containerId) {
        dockerClient.stopContainerCmd(containerId)
                .exec();
        log.info("停止容器,containerId={}", containerId);
        return Response.success();
    }

    /**
     * 暂停容器
     * @param containerId 容器ID
     * @return Response<Void>
     */
    @Override
    public Response<Void> pause(String containerId) {
        dockerClient.pauseContainerCmd(containerId).exec();
        log.info("暂停容器,containerId={}", containerId);
        return Response.success();
    }

    /**
     * 继续容器
     * @param containerId 容器ID
     * @return Response<Void>
     */
    @Override
    public Response<Void> unpause(String containerId) {
        dockerClient.unpauseContainerCmd(containerId).exec();
        log.info("继续容器,containerId={}", containerId);
        return Response.success();
    }

    @Override
    public Response<Void> restart(String containerId) {
        dockerClient.restartContainerCmd(containerId).exec();
        log.info("重启容器,containerId={}", containerId);
        return Response.success();
    }


    @Override
    public Response<Void> remove(String containerId, Map<String, String> param) {
        return null;
    }

    /**
     * 容器重命名
     * @param containerId 容器ID
     * @param newName 新的容器名;
     * @return Response<Void>
     */
    @Override
    public Response<Void> rename(String containerId, String newName) {
        dockerClient.renameContainerCmd(containerId).withName(newName).exec();
        log.info("重命名容器:[{}]为[{}]", containerId, newName);
        return Response.success();
    }

    @Override
    public Response<TopContainerResponse> top(String containerId, String psArgs) {
        return null;
    }

    @Override
    public Response<InspectContainerResponse> inspect(String containerId) {
        final InspectContainerResponse response =
                dockerClient.inspectContainerCmd(containerId).withSize(Boolean.TRUE).exec();
        log.info("获取容器详情,containerId={}", containerId);
        return Response.success(response);
    }
}
