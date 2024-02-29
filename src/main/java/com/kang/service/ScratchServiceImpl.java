package com.kang.service;

import com.kang.dao.*;
import com.kang.pojo.po.ActivityPo;
import com.kang.pojo.po.InvolvementPo;
import com.kang.pojo.po.ScratchingEventPo;
import com.kang.pojo.po.UserPo;
import com.kang.pojo.vo.ScratchingEventVo;
import com.kang.utils.Constants;
import com.kang.utils.LotteryUtil;
import com.kang.utils.UUIDGenerate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Transactional
@Service("ScratchServiceImpl")
public class ScratchServiceImpl implements ScratchService{
    @Autowired
    private ScratchMapper scratchMapper;
    @Autowired
    private InvolvementMapper involvementMapper;
    @Autowired
    private ActivityMapper activityMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private AssistanceMapper assistanceMapper;
    @Autowired
    private SharingMapper sharingMapper;

    @Override
    public Integer scratchingCard(String userId, String activityId){
        ActivityPo activityPo=activityMapper.queryActivityById(activityId);
        LotteryUtil lotteryUtil=new LotteryUtil(activityPo.getChance1(), activityPo.getChance2(), activityPo.getChance3(),
                activityPo.getChance4(), activityPo.getChance5());
        int cardId=lotteryUtil.generate();
        try{
            InvolvementPo involvementPo=involvementMapper.queryInvolvementByUserIdAndActivityId(userId, activityId);
            ScratchingEventPo scratchingEventPo=new ScratchingEventPo(UUIDGenerate.generate(), involvementPo.getInvolvementId(),cardId, new Date());
            scratchMapper.insertScratchingEvent(scratchingEventPo);
        }catch (Exception e){
            e.printStackTrace();
        }
        return cardId;
    }

    @Override
    public List<ScratchingEventVo> getScratchedEvents(String userId, String activityId){
        List<ScratchingEventVo> ans=new ArrayList<>();
        try{
            InvolvementPo involvementPo=involvementMapper.queryInvolvementByUserIdAndActivityId(userId, activityId);
            UserPo userPo=userMapper.queryAUserByUserId(userId);
            ActivityPo activityPo=activityMapper.queryActivityById(activityId);
            List<ScratchingEventPo> list=scratchMapper.queryScratchingEventsByInvolvementId(involvementPo.getInvolvementId());
            for(ScratchingEventPo item: list){
                ans.add(new ScratchingEventVo(item, userPo, activityPo));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return ans;
    }

    @Override
    public int getTotalScratchingCount(String userId, String activityId) {
        int sum=Constants.BaseScratchingCount;
        InvolvementPo involvementPo=involvementMapper.queryInvolvementByUserIdAndActivityId(userId, activityId);
        int assistanceCount=assistanceMapper.queryAssistEventsByInvolvementId(involvementPo.getInvolvementId()).size();
        sum+=assistanceCount*Constants.AssistanceAddScratchingCount;
        if(sharingMapper.querySharingEvent(involvementPo.getInvolvementId())!=null){
            sum+=Constants.FirstSharingAddScratchingCount;
        }
        return sum;
    }

    @Override
    public boolean isCardCollected(int cardId, String userId, String activityId) {
        InvolvementPo involvementPo=involvementMapper.queryInvolvementByUserIdAndActivityId(userId, activityId);
        List<ScratchingEventPo> scratchingEventPo=scratchMapper.querySpecificCard(involvementPo.getInvolvementId(), cardId);
        return scratchingEventPo.size() != 0;
    }


}
