package com.github.qy.dockerdashboardserver.controller;


import com.github.dockerjava.api.model.Image;
import com.github.qy.dockerdashboardserver.entity.Response;
import com.github.qy.dockerdashboardserver.service.ImageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin("*")
@Tag(name = "Docker镜像管理")
@RestController
@RequestMapping("/api/images")
@RequiredArgsConstructor
@Slf4j
@Validated
public class ImageController {
    private final ImageService service;
    @Operation(summary = "获取docker镜像列表")
    @GetMapping("/list")
    Response<List<Image>> getNetworkList() {
        List<Image> list = service.list();
        return Response.success(list);
    }
}
