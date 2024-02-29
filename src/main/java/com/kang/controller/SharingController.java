package com.kang.controller;

import com.kang.service.SharingService;
import com.kang.utils.JWTUtil;
import com.kang.utils.MsgCode;
import com.kang.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/share")
public class SharingController {

    @Autowired
    @Qualifier("SharingServiceImpl")
    private SharingService sharingService;

    @PostMapping("/generateUrl")
    public Result<String> generateSharingUrl(HttpServletRequest request, String activityId){
        String userId=getUserIdFromToken(request);
        if(sharingService.checkFirstTime(userId, activityId)){
            String url=sharingService.generateSharingUrl(userId, activityId);
            return new Result<>(MsgCode.OTHER_SUCCESS, "首次分享，额外获得两次刮卡机会", url);
        }
        String url=sharingService.getSharingUrl(userId, activityId);
        return new Result<>(MsgCode.OTHER_SUCCESS, "非首次分享，生成分享链接", url);
    }

    private String getUserIdFromToken(HttpServletRequest request){
        String token=request.getHeader("access-token");
        return JWTUtil.getUserId(token);
    }
}
