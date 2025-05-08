package com.github.qy.dockerdashboardserver.controller;


import com.github.dockerjava.api.model.Version;
import com.github.qy.dockerdashboardserver.entity.Response;
import com.github.qy.dockerdashboardserver.service.InfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@CrossOrigin("*")
@Tag(name = "Docker info控制器")
@RestController
@RequestMapping("/api/info")
@RequiredArgsConstructor
@Slf4j
public class InfoController {
    private final InfoService service;

    /**
     * 获取DockerInfo 和Docker Version
     * @return DockerInfo
     */
    @Operation(summary = "获取DockerInfo与DockerVersion")
    @GetMapping("")
    public Response<Map<String, Object>> getInfo() {
        return service.getInfo();
    }

    @Operation(summary = "获取DockerVersion")
    @GetMapping("/version")
    public Version getVersion() {
        return service.getVersion();
    }


}
