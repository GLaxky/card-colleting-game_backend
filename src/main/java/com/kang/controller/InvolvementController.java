package com.kang.controller;

import com.kang.pojo.po.ActivityPo;
import com.kang.pojo.vo.ActivityVo;
import com.kang.service.InvolvementService;
import com.kang.utils.JWTUtil;
import com.kang.utils.MsgCode;
import com.kang.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/involvement")
public class InvolvementController {

    @Autowired
    @Qualifier("InvolvementServiceImpl")
    private InvolvementService involvementService;

    @PostMapping("/participate")
    public Result<Object> participateActivity(HttpServletRequest request, String activityId){
        String userId=getUserIdFromToken(request);
        MsgCode ans=involvementService.participateActivity(userId, activityId);
        return new Result<>(ans, "参加集卡活动", null);
    }

    @PostMapping("/checkInvolvement")
    public Result<Boolean> checkInvolvement(HttpServletRequest request, String activityId){
        String userId=getUserIdFromToken(request);
        try {
            Boolean ans =involvementService.checkInvolvement(userId, activityId);
            return new Result<>(MsgCode.OTHER_SUCCESS, "查看是否参加活动", ans);
        }catch (Exception e){
            e.printStackTrace();
            return new Result<>(MsgCode.OTHER_FAILED, "查看是否参加活动", null);
        }
    }

    @GetMapping("/getParticipatedActivities")
    public Result<List<ActivityPo>> getParticipatedActivities(HttpServletRequest request){
        String userId=getUserIdFromToken(request);
        try {
            List<ActivityPo> ans=involvementService.getParticipatedActivities(userId);
            return new Result<>(MsgCode.OTHER_SUCCESS, "获取用户参加的活动列表", ans);
        }catch (Exception e){
            e.printStackTrace();
            return new Result<>(MsgCode.OTHER_FAILED, "获取用户参加的活动列表", null);
        }

    }

    @PostMapping("/getInvolvingCount")
    public Result<Integer> getInvolvingCount(HttpServletRequest request, String activityId){
        String userType=getUserTypeFromToken(request);
        if(!userType.equals("administrator")){
            return new Result<>(MsgCode.PERMISSION_DENY, "权限不足", null);
        }
        int ans=involvementService.getInvolvingCount(activityId);
        return new Result<>(MsgCode.OTHER_SUCCESS, "获取参加人数成功", ans);
    }

    @PostMapping("/getCompletedCount")
    public Result<Integer> getCompletedCount(HttpServletRequest request, String activityId){
        String userType=getUserTypeFromToken(request);
        if(!userType.equals("administrator")){
            return new Result<>(MsgCode.PERMISSION_DENY, "权限不足", null);
        }
        int ans=involvementService.getCompletedCount(activityId);
        return new Result<>(MsgCode.OTHER_SUCCESS, "获取集卡完全人数成功", ans);
    }

    private String getUserIdFromToken(HttpServletRequest request){
        String token=request.getHeader("access-token");
        return JWTUtil.getUserId(token);
    }

    private String getUserTypeFromToken(HttpServletRequest request){
        String token=request.getHeader("access-token");
        return JWTUtil.getUserType(token);
    }

}
