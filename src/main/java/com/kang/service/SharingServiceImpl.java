package com.kang.service;

import com.kang.dao.InvolvementMapper;
import com.kang.dao.SharingMapper;
import com.kang.pojo.po.InvolvementPo;
import com.kang.pojo.po.SharingEventPo;
import com.kang.utils.UUIDGenerate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Transactional
@Service("SharingServiceImpl")
public class SharingServiceImpl implements SharingService{

    @Autowired
    private SharingMapper sharingMapper;
    @Autowired
    private InvolvementMapper involvementMapper;

    @Override
    public boolean checkFirstTime(String userId, String activityId) {
        InvolvementPo involvementPo=involvementMapper.queryInvolvementByUserIdAndActivityId(userId, activityId);
        SharingEventPo sharingEventPo=sharingMapper.querySharingEvent(involvementPo.getInvolvementId());
        return sharingEventPo==null;
    }

    @Override
    public String generateSharingUrl(String userId, String activityId) {
        String url="?userId="+userId+"&activityId="+activityId;
        InvolvementPo involvementPo=involvementMapper.queryInvolvementByUserIdAndActivityId(userId, activityId);
        SharingEventPo sharingEventPo=new SharingEventPo
                (UUIDGenerate.generate(), involvementPo.getInvolvementId(), new Date(), url);
        sharingMapper.insertSharingEvent(sharingEventPo);
        return url;
    }

    @Override
    public String getSharingUrl(String userId, String activityId) {
        InvolvementPo involvementPo=involvementMapper.queryInvolvementByUserIdAndActivityId(userId, activityId);
        SharingEventPo sharingEventPo=sharingMapper.querySharingEvent(involvementPo.getInvolvementId());
        return sharingEventPo.getSharingUrl();
    }
}
