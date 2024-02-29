package com.kang.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kang.pojo.po.UserPo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompleteUserVo {
    private String userId;
    private String telephone;
    private String userName;
    private String nickName;
    private String sex;
    private String userType;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    private String password;



    public CompleteUserVo(UserPo userPo, String userType) {
        String[] sexName= {"保密", "女", "男"};
        this.userId=userPo.getUserId();
        this.telephone=userPo.getTelephone();
        this.userName=userPo.getUserName();
        this.nickName=userPo.getNickName();
        this.sex=sexName[userPo.getSex()];
        this.createTime=userPo.getCreateTime();
        this.password=userPo.getPassword();
        this.userType=userType;

    }
}
