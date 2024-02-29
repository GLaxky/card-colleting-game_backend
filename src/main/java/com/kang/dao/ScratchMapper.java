package com.kang.dao;

import com.kang.pojo.po.ScratchingEventPo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ScratchMapper {

    void insertScratchingEvent(ScratchingEventPo scratchingEventPo);

    List<ScratchingEventPo> queryScratchingEventsByInvolvementId(String involvementId);

    List<ScratchingEventPo> querySpecificCard(@Param("involvementId")String involvementId,@Param("cardId") int cardId);

}
