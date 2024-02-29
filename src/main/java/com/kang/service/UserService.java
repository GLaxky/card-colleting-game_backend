package com.kang.service;

//import com.kang.pojo.User;
import com.kang.pojo.vo.CompleteUserVo;
import com.kang.pojo.vo.SimpleUserVo;
import com.kang.utils.MsgCode;

public interface UserService {
//    User register(User user);
//    User login(User user);

    MsgCode loginCheck(SimpleUserVo simpleUserVo);

    MsgCode registerAUser(CompleteUserVo completeUserVo);

    MsgCode logout();

    CompleteUserVo getUserInfoByName(String userName);

    CompleteUserVo getUserInfoById(String userId);

    MsgCode editPersonalInfo(CompleteUserVo completeUserVo);
}
