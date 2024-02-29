package com.kang.dao;

public interface UserTypeMapper {

    String queryUserTypeNameById(int userTypeId);

    int queryUserTypeIdByName(String userType);
}
