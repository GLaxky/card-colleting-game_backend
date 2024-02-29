package com.kang.service;

import com.kang.pojo.po.ActivityPo;
import com.kang.pojo.vo.ActivityEditFields;
import com.kang.pojo.vo.ActivityVo;
import com.kang.utils.MsgCode;

import java.util.List;

public interface ActivityService {
    MsgCode createActivity(ActivityVo activityVo);

    MsgCode editActivity(ActivityVo activityVo, ActivityEditFields editFields);

    ActivityPo getActivityInfoById(String activityId);

    List<ActivityPo> getAllActivities();

    List<ActivityPo> getAllUpActivities();


}
