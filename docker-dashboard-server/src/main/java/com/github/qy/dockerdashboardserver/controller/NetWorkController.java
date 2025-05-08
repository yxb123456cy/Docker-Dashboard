package com.github.qy.dockerdashboardserver.controller;


import com.github.dockerjava.api.command.CreateNetworkResponse;
import com.github.dockerjava.api.model.Network;
import com.github.qy.dockerdashboardserver.entity.Response;
import com.github.qy.dockerdashboardserver.entity.dto.CreateNetWorkDTO;
import com.github.qy.dockerdashboardserver.service.NetWorkService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import java.util.List;

@CrossOrigin("*")
@Tag(name = "Docker网络管理")
@RestController
@RequestMapping("/api/network")
@RequiredArgsConstructor
@Validated
public class NetWorkController {
    private final NetWorkService service;

    /**
     * 获取Docker 网络列表;
     * @return Response<List < Network>>
     */
    @Operation(summary = "获取docker网络列表")
    @GetMapping("/list")
    Response<List<Network>> getNetworkList() {
        return service.getNetworkList();
    }

    @Operation(summary = "获取docker网络详情")
    @GetMapping("/info")
    Response<Network> getNetWorkById(@RequestParam("netWorkID") @NotBlank String netWorkID) {
        return service.getNetWorkById(netWorkID);
    }

    @Operation(summary = "创建docker网络")
    @PostMapping("/create")
    Response<CreateNetworkResponse> createNetWork(@RequestBody CreateNetWorkDTO netWorkDTO) {
        return service.createNetWork(netWorkDTO.getNetWorkName(), netWorkDTO.isIPV6(), netWorkDTO.getDriver());
    }

    @Operation(summary = "删除docker网络")
    @DeleteMapping("/delete")
    Response<Void> deleteNetWork(@RequestParam("netWorkID") @NotBlank String netWorkID) {
        return service.deleteNetWork(netWorkID);
    }
}
