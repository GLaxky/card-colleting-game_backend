package com.kang.controller;

import com.kang.pojo.vo.AssistanceEventVo;
import com.kang.service.AssistanceService;
import com.kang.utils.Constants;
import com.kang.utils.JWTUtil;
import com.kang.utils.MsgCode;
import com.kang.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/assistance")
public class AssistanceController {
    @Autowired
    @Qualifier("AssistanceServiceImpl")
    private AssistanceService assistanceService;

    @PostMapping("/assist")
    public Result<Object> assistOther(HttpServletRequest request, String activityId, String assistedId){
        String userId=getUserIdFromToken(request);
        if(userId.equals(assistedId)){
            return new Result<>(MsgCode.OTHER_FAILED, "用户不能助力自己", null);
        }
        if(Constants.AssistanceMaxCount<=assistanceService.getAllAssistEventsCountForUsers(userId, activityId)){
            return new Result<>(MsgCode.ASSISTANCE_LIMITED, "助力失败，助力达到上限", null);
        }
        MsgCode msgCode= assistanceService.assistOtherUser(userId, activityId, assistedId);
        if(msgCode==MsgCode.OTHER_SUCCESS){
            return new Result<>(MsgCode.OTHER_SUCCESS, "助力成功，为他增加了一次刮卡次数", null);
        }else{
            return new Result<>(MsgCode.OTHER_FAILED, "助力失败", null);
        }
    }

    @PostMapping("/getAssistanceRecords")
    public Result<List<AssistanceEventVo>> getAssistanceRecords(String activityId, String assistedUserId){
        List<AssistanceEventVo> ans= assistanceService.getAllAssistEventsForInvolvement(activityId, assistedUserId);
        return new Result<>(MsgCode.OTHER_SUCCESS, "获取助力流水", ans);
    }

    @PostMapping("/getAvailableAssistanceCont")
    public Result<Integer> getAvailableAssistanceCont(HttpServletRequest request, String activityId){
        String userId=getUserIdFromToken(request);
        int count=assistanceService.getAllAssistEventsCountForUsers(userId, activityId);
        return new Result<>(MsgCode.OTHER_SUCCESS, "获取剩余的助力次数", Constants.AssistanceMaxCount-count);
    }

    private String getUserIdFromToken(HttpServletRequest request){
        String token=request.getHeader("access-token");
        return JWTUtil.getUserId(token);
    }
}
