package com.github.qy.dockerdashboardserver.service.impl;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.CreateVolumeResponse;
import com.github.dockerjava.api.command.InspectVolumeResponse;
import com.github.dockerjava.api.command.ListVolumesResponse;
import com.github.qy.dockerdashboardserver.entity.Response;
import com.github.qy.dockerdashboardserver.service.VolumeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class VolumeServiceImpl implements VolumeService {
    private final DockerClient dockerClient;

    private void test() {
        //获取列表;
        //创建
        //详情;
        //删除;
    }

    @Override
    public Response<ListVolumesResponse> list() {
        ListVolumesResponse response = dockerClient.listVolumesCmd().exec();
        log.info("获取存储券列表:{}", response);
        return Response.success(response);
    }

    @Override
    public Response<CreateVolumeResponse> create(String name, String driver) {
        CreateVolumeResponse response = dockerClient.createVolumeCmd()
                .withDriver(driver)
                .withName(name)
                .exec();
        log.info("创建存储券,name:{},driver:{}", name, driver);
        return Response.success(response);
    }

    @Override
    public Response<InspectVolumeResponse> inspect(String name) {
        InspectVolumeResponse response = dockerClient.inspectVolumeCmd(name).exec();
        log.info("获取券详情 name:{}", name);
        return Response.success(response);
    }

    @Override
    public Response<Void> remove(String name) {
        dockerClient.removeVolumeCmd(name).exec();
        log.info("移除卷:{}完成", name);
        return Response.success();
    }
}
