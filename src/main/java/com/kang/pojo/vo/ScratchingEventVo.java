package com.kang.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kang.pojo.po.ActivityPo;
import com.kang.pojo.po.ScratchingEventPo;
import com.kang.pojo.po.UserPo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScratchingEventVo {
    private String scratchingEventId;
    private String activityId;
    private String userId;
    private String cardName;
    private int chance;
    private String cardPic;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date scratchingTime;

    public ScratchingEventVo(ScratchingEventPo scratchingEventPo, UserPo userPo, ActivityPo activityPo) {
        this.scratchingEventId=scratchingEventPo.getScratchingEventId();
        this.activityId=activityPo.getActivityId();
        this.userId=userPo.getUserId();
        this.scratchingTime=scratchingEventPo.getScratchingTime();
        switch (scratchingEventPo.getCardId()){
            case 0:
                this.cardName=activityPo.getCardName1();
                this.cardPic=activityPo.getCardPic1();
                this.chance=activityPo.getChance1();
                break;
            case 1:
                this.cardName=activityPo.getCardName2();
                this.cardPic=activityPo.getCardPic2();
                this.chance=activityPo.getChance2();
                break;
            case 2:
                this.cardName=activityPo.getCardName3();
                this.cardPic=activityPo.getCardPic3();
                this.chance=activityPo.getChance3();
                break;
            case 3:
                this.cardName=activityPo.getCardName4();
                this.cardPic=activityPo.getCardPic4();
                this.chance=activityPo.getChance4();
                break;
            case 4:
                this.cardName=activityPo.getCardName5();
                this.cardPic=activityPo.getCardPic5();
                this.chance=activityPo.getChance5();
                break;
        }
    }
}
