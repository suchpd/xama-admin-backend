package com.xama.backend.application.service.request;

import com.xama.backend.application.service.dto.MiniProgramLoginDto;
import com.xama.backend.domain.enumeration.Gender;
import com.xama.backend.infrastructure.mediatr.core.Request;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class MiniProgramLoginRequest implements Request<MiniProgramLoginDto> {
    @ApiModelProperty("登录Code")
    private String code;

    @ApiModelProperty("昵称")
    private String nickName;

    @ApiModelProperty("国家")
    private String country;

    @ApiModelProperty("省份")
    private String province;

    @ApiModelProperty("城市")
    private String city;

    @ApiModelProperty("性别")
    private Gender gender;

    @ApiModelProperty("语言")
    private String language;
}
