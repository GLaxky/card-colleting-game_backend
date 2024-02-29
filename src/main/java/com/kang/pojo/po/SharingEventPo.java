package com.kang.pojo.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SharingEventPo {
    private String sharingEventId;
    private String involvementId;
    private Date SharingTime;
    private String sharingUrl;
}
