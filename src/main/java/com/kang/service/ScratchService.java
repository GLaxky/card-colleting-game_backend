package com.kang.service;

import com.kang.pojo.vo.ScratchingEventVo;

import java.util.List;

public interface ScratchService {
    Integer scratchingCard(String userId, String activityId);

    List<ScratchingEventVo> getScratchedEvents(String userId, String activityId);

    int getTotalScratchingCount(String userId, String activityId);

    boolean isCardCollected(int cardId, String userId, String activityId);
}
