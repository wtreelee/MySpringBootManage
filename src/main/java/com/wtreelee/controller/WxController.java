package com.wtreelee.controller;

import com.wtreelee.common.HttpClientUtil;
import com.wtreelee.model.WxSessionModel;
import com.wtreelee.pojo.JsonResult;
import com.wtreelee.util.JsonUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/wx")
public class WxController {


    @RequestMapping("/wxLogin")
    public JsonResult doLogin(String code) {
        System.out.println("wxlogin - code: "+code);
        String url = "https://api.weixin.qq.com/sns/jscode2session";
        // appid=APPID&
        // secret=SECRET&
        // js_code=JSCODE&
        // grant_type=authorization_code
        Map<String,String> param = new HashMap<>();
        param.put("appid", "wx3085bb4e60246c94");
        param.put("secret", "b8842606607cb1abc6e728bb37530b78");
        param.put("js_code", code);
        param.put("grant_type", "authorization_code");
        String wxResult = HttpClientUtil.doGet(url,param);
        WxSessionModel model = JsonUtils.jsonToPojo(wxResult, WxSessionModel.class);
        // 存入session到redis

        System.out.println(wxResult);

        return JsonResult.ok();
    }
}
