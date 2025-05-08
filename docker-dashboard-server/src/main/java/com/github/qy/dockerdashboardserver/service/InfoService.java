package com.github.qy.dockerdashboardserver.service;



import com.github.dockerjava.api.model.Version;
import com.github.qy.dockerdashboardserver.entity.Response;

import java.util.Map;

public interface InfoService {

     Response<Map<String,Object>> getInfo();

     Version getVersion();

}
