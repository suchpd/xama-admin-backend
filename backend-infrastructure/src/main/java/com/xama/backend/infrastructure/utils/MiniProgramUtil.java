package com.xama.backend.infrastructure.utils;

public class MiniProgramUtil {
    private static final String APPID = "wx06f2c7bd024a9302";
    private static final String SECRET = "0c2ed66b1737a8152c5e5e0c4836cdc1";
    private static final String GRANT_TYPE = "authorization_code";

    /**
     * 获取微信认证地址
     * @param code  小程序登陆时获取的Code
     * @return  微信认证地址
     */
    public static StringBuffer appendAuthUrl(String code){
        StringBuffer authUrl = new StringBuffer("https://api.weixin.qq.com/sns/jscode2session?");

        authUrl.append("appid=").append(APPID);
        authUrl.append("&secret=").append(SECRET);
        authUrl.append("&js_code=").append(code);
        authUrl.append("&grant_type=").append(GRANT_TYPE);

        return authUrl;
    }
}
