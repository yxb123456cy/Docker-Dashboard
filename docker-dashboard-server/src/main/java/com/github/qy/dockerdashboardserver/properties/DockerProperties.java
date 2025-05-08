package com.github.qy.dockerdashboardserver.properties;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@ConfigurationProperties(prefix = "docker")
@Component
public class DockerProperties {
    private String connectType; //tcp;
    private String host;//主机名称
    private String port; //端口号;
}
