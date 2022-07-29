package com.xama.backend.application.service.request;

import com.xama.backend.application.service.dto.MiniProgramLoginDto;
import com.xama.backend.application.service.service.MiniProgramUserService;
import com.xama.backend.dao.repository.MiniProgramUserRepository;
import com.xama.backend.domain.dto.MiniProgramUserDto;
import com.xama.backend.infrastructure.mediatr.core.RequestHandler;
import com.xama.backend.infrastructure.utils.MiniProgramUtil;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Component
public class MiniProgramLoginRequestHandler implements RequestHandler<MiniProgramLoginRequest, MiniProgramLoginDto> {

    private final MiniProgramUserRepository miniProgramUserRepository;
    private final MiniProgramUserService miniProgramUserService;

    public MiniProgramLoginRequestHandler(MiniProgramUserRepository miniProgramUserRepository,
                                          MiniProgramUserService miniProgramUserService) {
        this.miniProgramUserRepository = miniProgramUserRepository;
        this.miniProgramUserService = miniProgramUserService;
    }

    @Override
    public MiniProgramLoginDto handle(MiniProgramLoginRequest request) {

        MiniProgramLoginDto loginData = new MiniProgramLoginDto();

        //登录
        Map<String,Object> loginCallbackData = login(request.getCode());

        if(loginCallbackData.containsKey("openid")){
            loginData.setOpenId(loginCallbackData.get("openid").toString());
        }

        if(loginCallbackData.containsKey("sessionKey")){
            loginData.setSessionKey(loginCallbackData.get("sessionKey").toString());
        }

        //判断是否注册
        boolean register = miniProgramUserRepository.existsByOpenId(loginData.getOpenId());

        if(!register){
            MiniProgramUserDto miniProgramUserDto = new MiniProgramUserDto(request.getNickName(),
                    loginData.getOpenId(),
                    loginData.getSessionKey(),
                    request.getCountry(),
                    request.getProvince(),
                    request.getCity(),
                    request.getGender(),
                    request.getLanguage(),
                    "127.0.0.1");

            miniProgramUserService.register(miniProgramUserDto);
        }

        return loginData;
    }

    /**
     * 登录
     * @param code  登录Code
     * @return  登录返回值
     */
    private Map<String,Object> login(String code){
        Map<String,Object> map = new HashMap<>();

        if(ObjectUtils.isEmpty(code)){
            throw new RuntimeException("登录Code不可为空，登录失败！");
        }

        String authUrl = MiniProgramUtil.appendAuthUrl(code).toString();

        //用RestTemplate请求Url，获取openid和session_key
        RestTemplate restTemplate = new RestTemplate();

        String response = restTemplate.getForObject(authUrl,String.class);

        if(ObjectUtils.isEmpty(response)){
            throw new RuntimeException("云端异常，登录失败！");
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
}
