package com.xama.backend.api.controller;

import com.xama.backend.application.service.MiniProgramUserService;
import com.xama.backend.domain.dto.MiniProgramUserDto;
import com.xama.backend.domain.entity.MiniProgramUser;
import com.xama.backend.domain.enumeration.Gender;
import com.xama.backend.infrastructure.utils.MiniProgramUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/miniProgram")
public class MiniProgramController {

    private final MiniProgramUserService miniProgramUserService;

    public MiniProgramController(MiniProgramUserService miniProgramUserService) {
        this.miniProgramUserService = miniProgramUserService;
    }

    @GetMapping("miniProgramLogin")
    public Map<String,Object> miniProgramLogin(String code){
        Map<String,Object> map = new HashMap<>();

        if(ObjectUtils.isEmpty(code)){
            map.put("success",false);
            return map;
        }

        String authUrl = MiniProgramUtil.appendAuthUrl(code).toString();

        //用RestTemplate请求Url，获取openid和session_key
        RestTemplate restTemplate = new RestTemplate();

        String response = restTemplate.getForObject(authUrl,String.class);

        if(ObjectUtils.isEmpty(response)){
            map.put("success",false);
            return map;
        }

        String openid = "",session_key = "";
        String[] responseInfos = response.split(",");

        session_key = responseInfos[0].substring(16,responseInfos[0].length() - 1);
        openid = responseInfos[1].substring(10,responseInfos[1].length() - 2);

        map.put("response",response);
        map.put("sessionKey",session_key);
        map.put("openid",openid);

        return map;
    }

    @PostMapping
    public void register(){
        MiniProgramUserDto miniProgramUserDto = new MiniProgramUserDto(
                "such",
                "openId",
                "sessionKey",
                "country",
                "province",
                "city",
                Gender.Male,
                "ZH",
                false,
                "127.0.0.1"
        );

        miniProgramUserService.register(miniProgramUserDto);
    }
}
