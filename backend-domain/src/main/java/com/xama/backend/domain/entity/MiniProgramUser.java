package com.xama.backend.domain.entity;

import com.xama.backend.domain.dto.MiniProgramUserDto;
import com.xama.backend.domain.enumeration.Gender;
import com.xama.backend.infrastructure.common.Entity;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@javax.persistence.Entity
@Table(name = "[MiniProgramUser]")
@Getter
@Setter(AccessLevel.PRIVATE)
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class MiniProgramUser extends Entity {

    /**
     * 昵称
     */
    @Column(name = "NickName")
    private String nickName;

    /**
     * OpenId
     */
    @Column(name = "OpenId")
    private String openId;

    /**
     * SessionKey
     */
    @Column(name = "SessionKey")
    private String sessionKey;

    /**
     * 国家
     */
    @Column(name = "Country")
    private String country;

    /**
     * 省份
     */
    @Column(name = "Province")
    private String province;

    /**
     * 城市
     */
    @Column(name = "City")
    private String city;

    /**
     * 性别
     */
    @Column(name = "Gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    /**
     * 语言
     */
    @Column(name = "Language")
    private String language;

    /**
     * 创建时间
     */
    @Column(name = "CreateTime")
    private Date createTime;

    /**
     * 锁定状态
     */
    @Column(name = "LockStatus")
    private boolean lockStatus;

    /**
     * 最后登录状态
     */
    @Column(name = "LastLoginTime")
    private Date lastLoginTime;

    /**
     * 最后登录Ip
     */
    @Column(name = "LastLoginIp")
    private String lastLoginIp;

    /**
     * 注册
     * @param miniProgramUserDto    注册信息
     * @return  用户
     */
    public static MiniProgramUser register(MiniProgramUserDto miniProgramUserDto){
        MiniProgramUser miniProgramUser = new MiniProgramUser();

        miniProgramUser.setNickName(miniProgramUserDto.getNickName());
        miniProgramUser.setOpenId(miniProgramUserDto.getOpenId());
        miniProgramUser.setSessionKey(miniProgramUserDto.getSessionKey());
        miniProgramUser.setCountry(miniProgramUserDto.getCountry());
        miniProgramUser.setProvince(miniProgramUserDto.getProvince());
        miniProgramUser.setCity(miniProgramUserDto.getCity());
        miniProgramUser.setGender(miniProgramUserDto.getGender());
        miniProgramUser.setLanguage(miniProgramUserDto.getLanguage());
        miniProgramUser.setCreateTime(new Date());
        miniProgramUser.setLastLoginTime(new Date());
        miniProgramUser.setLastLoginIp(miniProgramUserDto.getLastLoginIp());

        BeanUtils.copyProperties(miniProgramUserDto,miniProgramUser);

        return miniProgramUser;
    }
}
