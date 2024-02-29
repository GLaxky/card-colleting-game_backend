package com.kang.dao;

import com.kang.pojo.po.AssistanceEventPo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AssistanceMapper {
    void insertAssistEvent(AssistanceEventPo assistanceEventPo);

    List<AssistanceEventPo> queryAssistEventsByUserIdAndActivityId
            (@Param("userId") String userId, @Param("activityId") String activityId);

    List<AssistanceEventPo> queryAssistEventsByInvolvementId(String involvementId);

}
