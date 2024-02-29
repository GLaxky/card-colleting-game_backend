package com.kang.dao;

import com.kang.pojo.po.ScratchingEventPo;
import com.kang.pojo.po.SharingEventPo;

public interface SharingMapper {
    void insertSharingEvent(SharingEventPo scratchingEventPo);

    SharingEventPo querySharingEvent(String involvementId);
}
