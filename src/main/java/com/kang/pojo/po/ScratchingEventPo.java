package com.kang.pojo.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScratchingEventPo {
    private String scratchingEventId;
    private String involvementId;
    private int cardId;
    private Date ScratchingTime;
}
