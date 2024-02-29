package com.kang.controller;

import com.kang.pojo.po.ActivityPo;
import com.kang.pojo.vo.ActivityEditFields;
import com.kang.pojo.vo.ActivityVo;
import com.kang.service.ActivityService;
import com.kang.utils.FileSaving;
import com.kang.utils.JWTUtil;
import com.kang.utils.MsgCode;
import com.kang.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/activity")
public class ActivityController {

    @Autowired
    @Qualifier("ActivityServiceImpl")
    private ActivityService activityService;

    @PostMapping("/create")
    public Result<Object> createActivity(HttpServletRequest request, ActivityVo activityVo){
//        System.out.println(activityVo);
        String userType=getUserType(request);
        if(!userType.equals("administrator")){
            return new Result<>(MsgCode.PERMISSION_DENY, "权限不足", null);
        }
        // todo-重名的活动记得判断
        MsgCode ans=activityService.createActivity(activityVo);
//        FileSaving.upload(activityVo.getPostPic());
        return new Result<>(ans, "创建新集卡活动", null);
    }

    @PostMapping("/edit")
    public Result<Object> editActivity(HttpServletRequest request, ActivityVo activityVo, ActivityEditFields editFields){
//        System.out.println(activityVo);
//        System.out.println(editFields);
        String userType=getUserType(request);
        if(!userType.equals("administrator")){
            return new Result<>(MsgCode.PERMISSION_DENY, "权限不足", null);
        }
        MsgCode ans=activityService.editActivity(activityVo, editFields);
//        FileSaving.upload(activityVo.getPostPic());
        return new Result<>(ans, "编辑集卡活动", null);
    }

    @GetMapping("/all")
    public Result<List<ActivityPo>> getAllActivities(HttpServletRequest request){
        try{
            String userType=getUserType(request);
            if(!userType.equals("administrator")){
                return new Result<>(MsgCode.PERMISSION_DENY, "权限不足", null);
            }
            List<ActivityPo> ans=activityService.getAllActivities();
            return new Result<>(MsgCode.OTHER_SUCCESS, "查询所有活动成功", ans);
        }catch (Exception e){
            e.printStackTrace();
            return new Result<>(MsgCode.OTHER_FAILED, "查询所有活动失败", null);
        }
    }

    @GetMapping("/allUp")
    public Result<List<ActivityPo>> getAllUpActivities(){
        try{
            List<ActivityPo> ans=activityService.getAllUpActivities();
            return new Result<>(MsgCode.OTHER_SUCCESS, "查询所有上架活动成功", ans);
        }catch (Exception e){
            e.printStackTrace();
            return new Result<>(MsgCode.OTHER_FAILED, "查询所有上架活动失败", null);
        }
    }

    @GetMapping("/info")
    public Result<ActivityPo> getActivityInfo(String activityId){
        try{
            ActivityPo ans=activityService.getActivityInfoById(activityId);
            return new Result<>(MsgCode.OTHER_SUCCESS, "查询活动成功", ans);
        }catch (Exception e){
            e.printStackTrace();
            return new Result<>(MsgCode.OTHER_FAILED, "查询活动失败", null);
        }
    }

    private String getUserType(HttpServletRequest request){
        String token=request.getHeader("access-token");
        return JWTUtil.getUserType(token);
    }
}
