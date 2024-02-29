package com.kang.pojo.po;

import com.kang.pojo.vo.ActivityVo;
import com.kang.utils.FileSaving;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActivityPo {
    private String activityId;
    private String activityTitle;
    private String postPic;
    private Date createTime;
    private Date startTime;
    private Date endTime;
    private String rules;
    private int state;
    private String cardName1;
    private String cardName2;
    private String cardName3;
    private String cardName4;
    private String cardName5;
    private String cardPic1;
    private String cardPic2;
    private String cardPic3;
    private String cardPic4;
    private String cardPic5;
    private int chance1;
    private int chance2;
    private int chance3;
    private int chance4;
    private int chance5;

    public ActivityPo(ActivityVo activityVo) {
        this.activityId=activityVo.getActivityId();
        this.activityTitle=activityVo.getActivityTitle();
        this.postPic= FileSaving.upload(activityVo.getPostPic(), this.activityId);
        this.createTime=new Date();
        this.startTime=activityVo.getStartTime();
        this.endTime=activityVo.getEndTime();
        this.rules=activityVo.getRules();
        this.state=activityVo.getState();
        this.cardName1=activityVo.getCardName1();
        this.cardName2=activityVo.getCardName2();
        this.cardName3=activityVo.getCardName3();
        this.cardName4=activityVo.getCardName4();
        this.cardName5=activityVo.getCardName5();
        this.cardPic1=FileSaving.upload(activityVo.getCardPic1(), this.activityId);
        this.cardPic2=FileSaving.upload(activityVo.getCardPic2(), this.activityId);
        this.cardPic3=FileSaving.upload(activityVo.getCardPic3(), this.activityId);
        this.cardPic4=FileSaving.upload(activityVo.getCardPic4(), this.activityId);
        this.cardPic5=FileSaving.upload(activityVo.getCardPic5(), this.activityId);
        this.chance1=activityVo.getChance1();
        this.chance2=activityVo.getChance2();
        this.chance3=activityVo.getChance3();
        this.chance4=activityVo.getChance4();
        this.chance5=activityVo.getChance5();
    }
}
