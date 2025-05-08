package com.github.qy.dockerdashboardserver.service;

import com.github.dockerjava.api.command.InspectImageResponse;
import com.github.dockerjava.api.model.Image;
import com.github.dockerjava.api.model.PruneResponse;
import com.github.dockerjava.api.model.SearchItem;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.List;

public interface ImageService {
    /**
     * 获取镜像列表
     *
     *
     * @return List<Image>
     */
    List<Image> list();

    /**
     * 通过镜像ID获取镜像
     *
     * @param imageId 镜像ID
     * @return InspectImageResponse
     */
    InspectImageResponse inspect(String imageId);

    /**
     * 重新标记镜像
     *
     * @param imageId 镜像ID
     * @param newTag  新的镜像Tag
     */
    void tag(String imageId, String newTag);

    //拉取镜像 todo
    /**
     * 移除镜像
     *
     * @param imageId 镜像ID
     * @param force force
     *
     */
    void remove(String imageId, Boolean force);



    /**
     * 导出镜像
     *
     * @param imageId 镜像ID
     */
    void export(String imageId);

    /**
     * 搜索镜像
     *
     * @param key 关键词
     * @apiNote 仅仅在 Docker hub上搜索
     */
    List<SearchItem> search(String key);

    /**
     * 移除未使用的镜像
     */
    void remove();

    /**
     * 通过文件路径导入镜像导入镜像
     *
     * @param file 文件路径
     */
    void importByFile(String file);

    /**
     * 通过Tar文件导入镜像
     *
     * @param file tar文件位置;
     */
    void importByTar(String file);

    /**
     * @param imageId 镜像ID;
     */
    boolean exist(String imageId);

    /**
     * 清理镜像
     */
    PruneResponse pruneImage();

    /**
     * 镜像保存
     *
     * @return InputStream
     */
    InputStream save(String nameTag);

    /**
     * 批量镜像保存
     */
    void saveBatch(List<String> nameTagList, HttpServletRequest request, HttpServletResponse response);
}
