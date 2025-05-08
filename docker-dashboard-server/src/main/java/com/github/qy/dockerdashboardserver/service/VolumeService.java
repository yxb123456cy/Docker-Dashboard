package com.github.qy.dockerdashboardserver.service;

import com.github.dockerjava.api.command.CreateVolumeResponse;
import com.github.dockerjava.api.command.InspectVolumeResponse;
import com.github.dockerjava.api.command.ListVolumesResponse;
import com.github.qy.dockerdashboardserver.entity.Response;


public interface VolumeService {
    /**
     * 卷列表
     */
    Response<ListVolumesResponse> list();

    /**
     * 创建新的存储卷
     */
    Response<CreateVolumeResponse> create(String name, String driver);

    /**
     * 卷详情
     *
     * @param name 卷名
     */
    Response<InspectVolumeResponse> inspect(String name);

    /**
     * 移除卷
     *
     * @param name 卷名称
     */
    Response<Void> remove(String name);
}
