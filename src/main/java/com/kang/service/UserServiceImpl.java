package com.kang.service;

import com.kang.dao.UserMapper;
//import com.kang.pojo.User;
import com.kang.dao.UserTypeMapper;
import com.kang.pojo.po.UserPo;
import com.kang.pojo.vo.CompleteUserVo;
import com.kang.pojo.vo.SimpleUserVo;
import com.kang.utils.MsgCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Locale;
import java.util.UUID;

@Transactional
@Service("UserServiceImpl")
public class UserServiceImpl implements UserService{

    @Autowired
    private UserTypeMapper userTypeMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public MsgCode loginCheck(SimpleUserVo simpleUserVo) {
        UserPo userPo = userMapper.queryAUserByUserName(simpleUserVo.getUserName());
        if(userPo==null){
            return MsgCode.LOGIN_NO_ACCOUNT;
        } else if(!userPo.getPassword().equals(simpleUserVo.getPassword())){
            return MsgCode.LOGIN_UNMATCHED;
        }else{
            return MsgCode.LOGIN_SUCCESS;
        }
    }

    @Override
    public MsgCode registerAUser(CompleteUserVo completeUserVo) {
        completeUserVo.setUserId(UUID.randomUUID().toString().replace("-", ""));
        UserPo sameNameUser=userMapper.queryAUserByUserName(completeUserVo.getUserName());
        if(sameNameUser!=null)
            return MsgCode.REGISTER_USERNAME_EXISTED;
        System.out.println(completeUserVo);
        UserPo userPo=new UserPo(completeUserVo, userTypeMapper.queryUserTypeIdByName(completeUserVo.getUserType()));
        System.out.println(userPo);
        userMapper.insertAUser(userPo);
        return MsgCode.REGISTER_SUCCESS;
    }

    @Override
    public MsgCode logout() {
        // 前端把token扔掉就可以了
        return null;
    }

    @Override
    public CompleteUserVo getUserInfoByName(String userName) {
        UserPo userPo=userMapper.queryAUserByUserName(userName);
//        System.out.println(userPo.getUserTypeId());
        String typeName=userTypeMapper.queryUserTypeNameById(userPo.getUserTypeId());
        return new CompleteUserVo(userPo, typeName) ;
    }

    @Override
    public CompleteUserVo getUserInfoById(String userId) {
        UserPo userPo=userMapper.queryAUserByUserId(userId);
//        System.out.println(userPo.getUserTypeId());
        String typeName=userTypeMapper.queryUserTypeNameById(userPo.getUserTypeId());
        return new CompleteUserVo(userPo, typeName) ;
    }

    @Override
    public MsgCode editPersonalInfo(CompleteUserVo completeUserVo) {
//        return null;
        try {
            userMapper.updateAUser(new UserPo(completeUserVo, userTypeMapper.queryUserTypeIdByName(completeUserVo.getUserType())));
            return MsgCode.OTHER_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return MsgCode.OTHER_FAILED;
        }

    }
}
