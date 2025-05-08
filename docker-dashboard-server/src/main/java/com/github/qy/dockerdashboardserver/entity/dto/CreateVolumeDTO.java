package com.github.qy.dockerdashboardserver.entity.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;

@Data
@Accessors(chain = true)
public class CreateVolumeDTO {
    @NotBlank
    private String name;
    @NotBlank
    private String driver;
}
