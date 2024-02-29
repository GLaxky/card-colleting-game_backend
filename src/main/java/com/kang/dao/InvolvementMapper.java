package com.kang.dao;

import com.kang.pojo.po.ActivityPo;
import com.kang.pojo.po.InvolvementPo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface InvolvementMapper {
    void insertInvolvement(InvolvementPo involvementPo);

    InvolvementPo queryInvolvementByUserIdAndActivityId(@Param("userId") String userId, @Param("activityId")String activityId);

    List<ActivityPo> queryActivitiesByUserId(String userId);

    InvolvementPo queryInvolvementByInvolvementId(String involvementId);

    List<InvolvementPo> queryInvolvementByActivityId(String activityId);
}
