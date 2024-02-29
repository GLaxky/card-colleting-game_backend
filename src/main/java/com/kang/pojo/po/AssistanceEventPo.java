package com.kang.pojo.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssistanceEventPo {
    private String assistEventId;
    private String involvementId;
    private String userId;
    private String activityId;
    private Date assistTime;
}
