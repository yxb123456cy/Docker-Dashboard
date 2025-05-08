package com.github.qy.dockerdashboardserver.entity.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;

@Data
@Accessors(chain = true)
public class CreateNetWorkDTO {
    @NotBlank
    private String netWorkName;
    private boolean isIPV6;
    @NotBlank
    private String driver;
}
