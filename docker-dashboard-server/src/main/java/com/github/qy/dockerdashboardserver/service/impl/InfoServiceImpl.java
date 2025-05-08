package com.github.qy.dockerdashboardserver.service.impl;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.model.Info;
import com.github.dockerjava.api.model.Version;
import com.github.qy.dockerdashboardserver.entity.Response;
import com.github.qy.dockerdashboardserver.service.InfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class InfoServiceImpl implements InfoService {
    private final DockerClient dockerClient;


    @Override
    public Response<Map<String, Object>> getInfo() {
        final Info info = dockerClient.infoCmd().exec();
        final Version version = dockerClient.versionCmd().exec();
        Map<String, Object> map = new HashMap<>();
        map.put("info", info);
        map.put("version", version);
        return Response.success(map);
    }

    @Override
    public Version getVersion() {
        return dockerClient.versionCmd().exec();
    }
}
