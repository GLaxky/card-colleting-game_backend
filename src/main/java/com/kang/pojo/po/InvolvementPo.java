package com.kang.pojo.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvolvementPo {
    private String involvementId;
    private String userId;
    private String activityId;
    private Date involvementTime;
}
