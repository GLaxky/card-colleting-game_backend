package com.kang.service;

import com.kang.dao.InvolvementMapper;
import com.kang.dao.ScratchMapper;
import com.kang.pojo.po.ActivityPo;
import com.kang.pojo.po.InvolvementPo;
import com.kang.pojo.vo.ActivityVo;
import com.kang.utils.MsgCode;
import com.kang.utils.UUIDGenerate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Transactional
@Service("InvolvementServiceImpl")
public class InvolvementServiceImpl implements InvolvementService{

    @Autowired
    private InvolvementMapper involvementMapper;

    @Autowired
    private ScratchMapper scratchMapper;

    @Override
    public MsgCode participateActivity(String userId, String activityId) {
        if(checkInvolvement(userId, activityId)){
            return MsgCode.PARTICIPATION_DUPLICATION;
        }
        InvolvementPo involvementPo=new InvolvementPo(UUIDGenerate.generate(),userId, activityId, new Date());
        try{
            involvementMapper.insertInvolvement(involvementPo);
            return MsgCode.OTHER_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return MsgCode.OTHER_FAILED;
        }
    }

    @Override
    public boolean checkInvolvement(String userId, String activityId) {
//        System.out.println(userId);
//        System.out.println(activityId);
        InvolvementPo ans=involvementMapper.queryInvolvementByUserIdAndActivityId(userId, activityId);
//        System.out.println(ans);
        return ans != null;
    }

    @Override
    public List<ActivityPo> getParticipatedActivities(String userId) {
        return involvementMapper.queryActivitiesByUserId(userId);
    }

    @Override
    public int getInvolvingCount(String activityId) {
        return involvementMapper.queryInvolvementByActivityId(activityId).size();
    }

    @Override
    public int getCompletedCount(String activityId) {
        List<InvolvementPo> list=involvementMapper.queryInvolvementByActivityId(activityId);
        int count=0;
        for(InvolvementPo item: list){
            InvolvementPo involvementPo=involvementMapper.queryInvolvementByUserIdAndActivityId(item.getUserId(), activityId);
            if((scratchMapper.querySpecificCard(involvementPo.getInvolvementId(), 0).size()!=0)
                &&(scratchMapper.querySpecificCard(involvementPo.getInvolvementId(), 1).size()!=0)
                &&(scratchMapper.querySpecificCard(involvementPo.getInvolvementId(), 2).size()!=0)
                &&(scratchMapper.querySpecificCard(involvementPo.getInvolvementId(), 3).size()!=0)
                &&(scratchMapper.querySpecificCard(involvementPo.getInvolvementId(), 4).size()!=0)){
                count++;
            }
        }
        return count;
    }


}
