package com.kang.service;

import com.kang.dao.AssistanceMapper;
import com.kang.dao.InvolvementMapper;
import com.kang.pojo.po.AssistanceEventPo;
import com.kang.pojo.po.InvolvementPo;
import com.kang.pojo.vo.AssistanceEventVo;
import com.kang.utils.MsgCode;
import com.kang.utils.UUIDGenerate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Transactional
@Service("AssistanceServiceImpl")
public class AssistanceServiceImpl implements AssistanceService{

    @Autowired
    private AssistanceMapper assistanceMapper;

    @Autowired
    private InvolvementMapper involvementMapper;

    @Override
    public MsgCode assistOtherUser(String assistId, String activityId, String assistedId) {
        try{
            InvolvementPo involvementPo=involvementMapper.queryInvolvementByUserIdAndActivityId(assistedId, activityId);
            AssistanceEventPo assistanceEventPo=new AssistanceEventPo
                    (UUIDGenerate.generate(),involvementPo.getInvolvementId(), assistId, activityId, new Date());
            assistanceMapper.insertAssistEvent(assistanceEventPo);
            return MsgCode.OTHER_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return MsgCode.OTHER_FAILED;
        }

    }

    @Override
    public int getAllAssistEventsCountForUsers(String assistId, String activityId) {
        List<AssistanceEventPo> list=assistanceMapper.queryAssistEventsByUserIdAndActivityId(assistId, activityId);
        return list.size();
    }

    @Override
    public List<AssistanceEventVo> getAllAssistEventsForInvolvement(String activityId, String assistedId) {
        InvolvementPo involvementPo=involvementMapper.queryInvolvementByUserIdAndActivityId(assistedId, activityId);
        List<AssistanceEventVo> ans=new ArrayList<>();
        List<AssistanceEventPo> list=assistanceMapper.queryAssistEventsByInvolvementId(involvementPo.getInvolvementId());
        for(AssistanceEventPo item: list){
            ans.add(new AssistanceEventVo(item.getAssistEventId(), assistedId, item.getUserId(), activityId, item.getAssistTime()));
        }
        return ans;
    }
}
