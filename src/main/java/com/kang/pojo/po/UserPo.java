package com.kang.pojo.po;

import com.kang.pojo.vo.CompleteUserVo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserPo {
    private String userId;
    private String telephone;
    private String userName;
    private String nickName;
    private int sex;
    private int userTypeId;
    private Date createTime;
    private String password;

    public UserPo(CompleteUserVo completeUserVo, int userTypeId) {
        Map<String, Integer> sexMap=new HashMap<String, Integer>(){{
            put("保密", 0);
            put("女", 1);
            put("男", 2);
        }};
        this.userId=completeUserVo.getUserId();
        this.telephone=completeUserVo.getTelephone();
        this.userName=completeUserVo.getUserName();
        this.nickName=completeUserVo.getNickName();
        this.sex=sexMap.get(completeUserVo.getSex());
        this.createTime=completeUserVo.getCreateTime();
        this.password=completeUserVo.getPassword();
        this.userTypeId=userTypeId;
//        System.out.println(this.userType);
    }
}
