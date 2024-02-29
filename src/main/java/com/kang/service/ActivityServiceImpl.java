package com.kang.service;

import com.kang.dao.ActivityMapper;
import com.kang.pojo.po.ActivityPo;
import com.kang.pojo.vo.ActivityEditFields;
import com.kang.pojo.vo.ActivityVo;
import com.kang.utils.FileSaving;
import com.kang.utils.MsgCode;
import com.kang.utils.UUIDGenerate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service("ActivityServiceImpl")
public class ActivityServiceImpl implements ActivityService{

    @Autowired
    private ActivityMapper activityMapper;

    @Override
    public MsgCode createActivity(ActivityVo activityVo) {
        activityVo.setActivityId(UUIDGenerate.generate());
        ActivityPo activityPo=new ActivityPo(activityVo);
        try{
            activityMapper.insertActivity(activityPo);
        }catch (Exception e){
            return MsgCode.CREATE_ACTIVITY_FAILED;
        }
        return MsgCode.CREATE_ACTIVITY_SUCCESS;
    }

    @Override
    public MsgCode editActivity(ActivityVo activityVo, ActivityEditFields editFields) {
        ActivityPo activityPo=constructNewActivityPo(activityVo, editFields);
        try {
            activityMapper.updateActivity(activityPo);
            return MsgCode.EDIT_ACTIVITY_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return MsgCode.EDIT_ACTIVITY_FAILED;
        }

    }

    @Override
    public ActivityPo getActivityInfoById(String activityId) {
        return activityMapper.queryActivityById(activityId);
    }

    @Override
    public List<ActivityPo> getAllActivities() {
        return activityMapper.queryAllActivities();
    }

    @Override
    public List<ActivityPo> getAllUpActivities() {
        return activityMapper.queryAllUpActivities();
    }

    private ActivityPo constructNewActivityPo(ActivityVo activityVo, ActivityEditFields editFields){
        ActivityPo ans=activityMapper.queryActivityById(activityVo.getActivityId());
        if(editFields.isActivityTitleField()){
            ans.setActivityTitle(activityVo.getActivityTitle());
        }

        if(editFields.isPostPicField()){
            ans.setPostPic(FileSaving.upload(activityVo.getPostPic(), activityVo.getActivityId()));
        }

        if(editFields.isStateField()){
            ans.setState(activityVo.getState());
        }

        if(editFields.isStartTimeField()){
            ans.setStartTime(activityVo.getStartTime());
        }

        if(editFields.isEndTimeField()){
            ans.setEndTime(activityVo.getEndTime());
        }

        if(editFields.isRulesField()){
            ans.setRules(activityVo.getRules());
        }

        if(editFields.isCardName1Field()){
            ans.setCardName1(activityVo.getCardName1());
        }

        if(editFields.isCardName2Field()){
            ans.setCardName2(activityVo.getCardName2());
        }

        if(editFields.isCardName3Field()){
            ans.setCardName3(activityVo.getCardName3());
        }

        if(editFields.isCardName4Field()){
            ans.setCardName4(activityVo.getCardName4());
        }

        if(editFields.isCardName5Field()){
            ans.setCardName5(activityVo.getCardName5());
        }

        if(editFields.isCardPic1Field()){
            ans.setCardPic1(FileSaving.upload(activityVo.getCardPic1(), activityVo.getActivityId()));
        }

        if(editFields.isCardPic2Field()){
            ans.setCardPic2(FileSaving.upload(activityVo.getCardPic2(), activityVo.getActivityId()));
        }

        if(editFields.isCardPic3Field()){
            ans.setCardPic3(FileSaving.upload(activityVo.getCardPic3(), activityVo.getActivityId()));
        }

        if(editFields.isCardPic4Field()){
            ans.setCardPic4(FileSaving.upload(activityVo.getCardPic4(), activityVo.getActivityId()));
        }

        if(editFields.isCardPic5Field()){
            ans.setCardPic5(FileSaving.upload(activityVo.getCardPic5(), activityVo.getActivityId()));
        }

        if(editFields.isChance1Field()){
            ans.setChance1(activityVo.getChance1());
        }

        if(editFields.isChance2Field()){
            ans.setChance2(activityVo.getChance2());
        }

        if(editFields.isChance3Field()){
            ans.setChance3(activityVo.getChance3());
        }

        if(editFields.isChance4Field()){
            ans.setChance4(activityVo.getChance4());
        }

        if(editFields.isChance5Field()){
            ans.setChance5(activityVo.getChance5());
        }

        return ans;
    }
}
