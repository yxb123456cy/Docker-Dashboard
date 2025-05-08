package com.github.qy.dockerdashboardserver.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@Tag(name = "Docker客户端")
@RestController
@RequestMapping("/api/endpoint")
@RequiredArgsConstructor
@Slf4j
public class ClientController {
}
