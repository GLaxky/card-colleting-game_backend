package com.kang.dao;

import com.kang.pojo.po.ActivityPo;

import java.util.List;

public interface ActivityMapper {
    void insertActivity(ActivityPo activityPo);

    void updateActivity(ActivityPo activityPo);

    ActivityPo queryActivityByTitle(String activityTitle);

    ActivityPo queryActivityById(String activityId);

    List<ActivityPo> queryAllActivities();

    List<ActivityPo> queryAllUpActivities();
}
