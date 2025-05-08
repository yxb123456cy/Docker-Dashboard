package com.github.qy.dockerdashboardserver;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.model.Info;
import com.github.dockerjava.api.model.Version;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@Slf4j
@SpringBootApplication
public class DockerDashboardServerApplication implements ApplicationRunner {
    private final DockerClient dockerClient;

    @Autowired
    public DockerDashboardServerApplication(DockerClient dockerClient) {
        this.dockerClient = dockerClient;
    }

    public static void main(String[] args) {
        SpringApplication.run(DockerDashboardServerApplication.class, args);
    }


    @Override
    public void run(ApplicationArguments args) throws Exception {
        final String banner = "                                     ██                ██ ██                    ██              ██                  ██                   \n" +
                " ██████                             ░██               ░██░░                    ░██             ░██                 ░██                   \n" +
                "░██░░░██  ██████   ███████   █████  ░██        █████  ░██ ██  █████  ███████  ██████           ░██  ██████   █████ ░██  ██  █████  ██████\n" +
                "░██  ░██ ░░░░░░██ ░░██░░░██ ██░░░██ ░██ █████ ██░░░██ ░██░██ ██░░░██░░██░░░██░░░██░  █████  ██████ ██░░░░██ ██░░░██░██ ██  ██░░░██░░██░░█\n" +
                "░██████   ███████  ░██  ░██░███████ ░██░░░░░ ░██  ░░  ░██░██░███████ ░██  ░██  ░██  ░░░░░  ██░░░██░██   ░██░██  ░░ ░████  ░███████ ░██ ░ \n" +
                "░██░░░   ██░░░░██  ░██  ░██░██░░░░  ░██      ░██   ██ ░██░██░██░░░░  ░██  ░██  ░██        ░██  ░██░██   ░██░██   ██░██░██ ░██░░░░  ░██   \n" +
                "░██     ░░████████ ███  ░██░░██████ ███      ░░█████  ███░██░░██████ ███  ░██  ░░██       ░░██████░░██████ ░░█████ ░██░░██░░██████░███   \n" +
                "░░       ░░░░░░░░ ░░░   ░░  ░░░░░░ ░░░        ░░░░░  ░░░ ░░  ░░░░░░ ░░░   ░░    ░░         ░░░░░░  ░░░░░░   ░░░░░  ░░  ░░  ░░░░░░ ░░░    \n";
        System.out.println(banner);
        dockerClient.pingCmd().exec();
        Info info = dockerClient.infoCmd().exec();
        Version version = dockerClient.versionCmd().exec();
        log.info("docker info:{}", info);
        log.info("docker version:{}", version);
    }
}
