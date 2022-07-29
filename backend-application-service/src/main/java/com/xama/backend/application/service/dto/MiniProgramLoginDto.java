package com.xama.backend.application.service.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class MiniProgramLoginDto {

    @ApiModelProperty("OpenId")
    private String openId;

    @ApiModelProperty("SessionKey")
    private String sessionKey;
}
