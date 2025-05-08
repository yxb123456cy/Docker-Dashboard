package com.github.qy.dockerdashboardserver.service.impl;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.CreateNetworkResponse;
import com.github.dockerjava.api.command.InspectNetworkCmd;
import com.github.dockerjava.api.model.Network;
import com.github.qy.dockerdashboardserver.entity.Response;
import com.github.qy.dockerdashboardserver.service.NetWorkService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class NetWorkServiceImpl implements NetWorkService {
    private final DockerClient dockerClient;

    private void test() {
        //获取网络列表;docker network list
        List<Network> networkList = dockerClient.listNetworksCmd().exec();
        //创建Docker 容器网络 docker network create jdk
        dockerClient.createNetworkCmd()
                .withEnableIpv6(false)
                .withName("jdk")
                .withDriver("bridge")
                .exec();
        //获取网络详情;docker network inspect docker容器网络ID;
        InspectNetworkCmd cmd = dockerClient.inspectNetworkCmd()
                .withNetworkId("ssss");
        Network network = cmd.exec();
        //删除单个docker容器网络;
        dockerClient.removeNetworkCmd("").exec();

    }

    /**
     * 获取Docker 网络列表 cli: docker network list
     * @return Response<List < Network>>
     */
    @Override
    public Response<List<Network>> getNetworkList() {
        List<Network> networkList = dockerClient.listNetworksCmd().exec();
        return Response.success(networkList);
    }

    /**
     * 获取单个Docker网络 详情;
     * @param netWorkID 网络ID cli:docker network inspect networkID;
     * @return Response<Network>
     */

    @Override
    public Response<Network> getNetWorkById(String netWorkID) {
        final Network network = dockerClient.inspectNetworkCmd()
                .withNetworkId(netWorkID)
                .exec();
        return Response.success(network);
    }

    /**
     * 创建一个Docker网络 cli: docker network create networkName;
     * @param netWorkName 网络名称
     * @param isIPV6 是否开启IPV6
     * @param driver Driver (bridge host null)
     * @return Response<CreateNetworkResponse>
     */
    @Override
    public Response<CreateNetworkResponse> createNetWork(String netWorkName, boolean isIPV6, String driver) {
        CreateNetworkResponse response = dockerClient.createNetworkCmd()
                .withName(netWorkName)
                .withEnableIpv6(isIPV6)
                .withDriver(driver)
                .exec();
        return Response.success(response);
    }

    /**
     * 删除单个Docker网络;
     * @param netWorkID 网络ID;
     * @return Response<Void>
     */
    @Override
    public Response<Void> deleteNetWork(String netWorkID) {
        dockerClient.removeNetworkCmd(netWorkID).exec();
        return Response.success();
    }
}
