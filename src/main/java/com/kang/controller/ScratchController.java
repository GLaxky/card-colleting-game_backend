package com.kang.controller;

import com.kang.pojo.vo.ScratchingEventVo;
import com.kang.service.InvolvementService;
import com.kang.service.ScratchService;
import com.kang.utils.JWTUtil;
import com.kang.utils.MsgCode;
import com.kang.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/scratch")
public class ScratchController {
    @Autowired
    @Qualifier("ScratchServiceImpl")
    private ScratchService scratchService;

    @Autowired
    @Qualifier("InvolvementServiceImpl")
    private InvolvementService involvementService;

    @PostMapping("/scratching")
    public Result<Integer> scratching(HttpServletRequest request, String activityId){
        String userId=getUserIdFromToken(request);
        if(!involvementService.checkInvolvement(userId, activityId)){
            return new Result<>(MsgCode.SCRATCH_FORBIDDEN_DUE_TO_NO_PARTICIPATION, "还未参加该活动，无法刮卡",null);
        }
        int totalCount=scratchService.getTotalScratchingCount(userId, activityId);
        int scratchedCount=scratchService.getScratchedEvents(userId, activityId).size();
        if(totalCount<=scratchedCount){
            return new Result<>(MsgCode.SCRATCH_LIMITED, "刮卡到达上限", null);
        }
        Integer ans=scratchService.scratchingCard(userId ,activityId);
        return new Result<>(MsgCode.OTHER_SUCCESS, "刮卡成功", ans);
    }

    @PostMapping("/getTotalScratchingCount")
    public Result<Integer> getTotalScratchingCount(HttpServletRequest request, String activityId){
        String userId=getUserIdFromToken(request);
        int totalCount=scratchService.getTotalScratchingCount(userId, activityId);
        return new Result<>(MsgCode.OTHER_SUCCESS, "获取可以刮卡的总次数", totalCount);
    }

    @PostMapping("/getScratchedEvents")
    public Result<List<ScratchingEventVo>> getScratchedEvents(HttpServletRequest request, String activityId){
        String userId=getUserIdFromToken(request);
        List<ScratchingEventVo> ans=scratchService.getScratchedEvents(userId, activityId);
        return new Result<>(MsgCode.OTHER_SUCCESS, "获得用户在该任务中的所有刮卡事件", ans);
    }

    @PostMapping("/getCollectionsProgress")
    public Result<List<Boolean>> getCollectionsProgress(HttpServletRequest request, String activityId){
        String userId=getUserIdFromToken(request);
        List<Boolean> ans=new ArrayList<>(5);
        for(int i=0;i<5;i++){
            boolean tmp=scratchService.isCardCollected(i, userId, activityId);
            ans.add(tmp);
        }
        return new Result<>(MsgCode.OTHER_SUCCESS, "获取集卡进度成功", ans);
    }

    private String getUserIdFromToken(HttpServletRequest request){
        String token=request.getHeader("access-token");
        return JWTUtil.getUserId(token);
    }

}
