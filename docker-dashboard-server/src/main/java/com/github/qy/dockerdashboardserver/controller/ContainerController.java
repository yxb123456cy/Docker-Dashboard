package com.github.qy.dockerdashboardserver.controller;


import com.github.dockerjava.api.command.InspectContainerResponse;
import com.github.dockerjava.api.command.TopContainerResponse;
import com.github.dockerjava.api.model.Container;
import com.github.qy.dockerdashboardserver.entity.Response;
import com.github.qy.dockerdashboardserver.service.ContainerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Map;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/container")
@Tag(name = "Docker容器管理")
@RequiredArgsConstructor
@Validated
public class ContainerController extends BaseController {
    private final ContainerService service;

    //获取容器列表
    @GetMapping("/list")
    @Operation(summary = "获取容器列表")
    public Response<List<Container>> list(@RequestParam("showAll") boolean showAll) {
        return service.list(true);
    }

    /**
     * 运行一个已经存在的容器
     *
     * @param containerId 容器ID
     */
    @PostMapping("/start")
    @Operation(summary = "运行一个已经存在的容器")
    public Response<Void> start(@RequestParam("containerId") @NotBlank String containerId) {
        return service.start(containerId);
    }

    /**
     * 重启Docker容器
     * @param containerId 容器ID
     * @return Response<Void>
     */
    @PostMapping("/restart")
    @Operation(summary = "重启Docker容器")
    Response<Void> restart(@RequestParam("containerId")@NotBlank String containerId) {
        return service.restart(containerId);
    }

    /**
     * 停止容器
     *
     * @param containerId 停止的容器ID
     */
    @PostMapping("/stop")
    @Operation(summary = "停止容器")
    public Response<Void> stop(@RequestParam("containerId")@NotBlank String containerId) {
        return service.stop(containerId);
    }

    /**
     * 暂停容器
     *
     * @apiNote 暂停指定容器(异步暂停)
     */
    @PostMapping("/pause")
    @Operation(summary = "暂停指定容器(异步暂停)")
    public Response<Void> pause(@RequestParam("containerId") @NotBlank String containerId) {
        return service.pause(containerId);
    }

    /**
     * 取消暂停容器
     *
     * @param containerId 容器ID
     * @apiNote 取消暂停容器
     */
    @PostMapping("/unpause")
    @Operation(summary = "取消暂停容器")
    public Response<Void> unpause(@RequestParam("containerId")@NotBlank String containerId) {
        return service.unpause(containerId);
    }


    /**
     * 移除指定容器
     *
     * @param containerId 容器ID
     * @param param       辅助操作的参数
     */
    @DeleteMapping("/remove")
    @Operation(summary = "移除指定容器")
    public Response<Void> remove(@RequestParam("containerId") @NotBlank String containerId, Map<String, String> param) {
        return null;
    }

    /**
     * 容器重命名
     *
     * @param containerId 容器ID
     */
    @PostMapping("/rename")
    @Operation(summary = "容器重命名")
    public Response<Void> rename(@RequestParam("containerId") @NotBlank String containerId, @RequestParam("containerId")@NotBlank String newName) {
        return service.rename(containerId, newName);
    }

    /**
     * 查询容器的线程信息
     *
     * @param containerId 容器ID
     * @param psArgs      进程参数
     * @return TopContainerResponse
     */
    @PostMapping("/top")
    @Operation(summary = "查询容器的线程信息")
    public Response<TopContainerResponse> top(@RequestParam("containerId") @NotBlank String containerId, String psArgs) {
        return null;
    }

    /**
     * 查询容器详情
     *
     * @param containerId  容器ID
     * @return 容器详情数据;
     */
    @PostMapping("/inspect")
    @Operation(summary = "查询容器详情")
    public Response<InspectContainerResponse> inspect(@RequestParam("containerId") @NotBlank String containerId) {
        return service.inspect(containerId);
    }


}
