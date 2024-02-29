package com.kang.service;

import com.kang.pojo.po.ActivityPo;
import com.kang.pojo.vo.ActivityVo;
import com.kang.utils.MsgCode;

import java.util.List;

public interface InvolvementService {
    MsgCode participateActivity(String userId, String activityId);

    boolean checkInvolvement(String userId , String activityId);

    List<ActivityPo> getParticipatedActivities(String userId);

    int getInvolvingCount(String activityId);

    int getCompletedCount(String activityId);
}
