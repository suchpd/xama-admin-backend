package com.xama.backend.domain.dto;

import com.xama.backend.domain.enumeration.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class MiniProgramUserDto {
    /**
     * 昵称
     */
    private String nickName;

    /**
     * OpenId
     */
    private String openId;

    /**
     * SessionKey
     */
    private String sessionKey;

    /**
     * 国家
     */
    private String country;

    /**
     * 省份
     */
    private String province;

    /**
     * 城市
     */
    private String city;

    /**
     * 性别
     */
    private Gender gender;

    /**
     * 语言
     */
    private String language;

    /**
     * 最后登录Ip
     */
    private String lastLoginIp;
}
