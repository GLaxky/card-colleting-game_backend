package com.kang.service;

import com.kang.pojo.vo.AssistanceEventVo;
import com.kang.utils.MsgCode;

import java.util.List;

public interface AssistanceService {
    MsgCode assistOtherUser(String assistId, String activityId, String assistedId);

    int getAllAssistEventsCountForUsers(String assistId, String activityId);

    List<AssistanceEventVo> getAllAssistEventsForInvolvement(String activityId, String assistedId);
}
