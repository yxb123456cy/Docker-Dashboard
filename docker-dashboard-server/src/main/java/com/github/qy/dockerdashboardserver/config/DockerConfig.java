package com.github.qy.dockerdashboardserver.config;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.core.DockerClientBuilder;
import com.github.dockerjava.httpclient5.ApacheDockerHttpClient;
import com.github.qy.dockerdashboardserver.properties.DockerProperties;
import lombok.Data;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.URI;
import java.time.Duration;

@Data
@Configuration
public class DockerConfig {
    /*
  将DockerClient注册为一个Bean;
   */
    private final DockerProperties properties;

    @Bean
    public DockerClient dockerClient() {
        String dockerUrl = properties.getConnectType() + "://" + properties.getHost() + ":" + properties.getPort();
        ApacheDockerHttpClient.Builder clientBuilder = new ApacheDockerHttpClient.Builder()
                .dockerHost(URI.create(dockerUrl)) // 替换为你的 Docker 主机地址
                .maxConnections(100)
                .connectionTimeout(Duration.ofSeconds(30))
                .responseTimeout(Duration.ofSeconds(45));
        return DockerClientBuilder.getInstance()
                .withDockerHttpClient(clientBuilder.build())
                .build();
    }
}
