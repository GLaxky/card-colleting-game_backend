package com.kang.dao;

import com.kang.pojo.po.UserPo;

public interface UserMapper {

    void insertAUser(UserPo userPo);

    void updateAUser(UserPo userPo);

    UserPo queryAUserByUserId(String userId);

    UserPo queryAUserByUserName(String userName);

    void deleteAUser(String userId);
}
