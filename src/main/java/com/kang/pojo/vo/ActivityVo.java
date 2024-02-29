package com.kang.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActivityVo {
    private String activityId;
    private String activityTitle;
    private MultipartFile postPic;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;
    private String rules;
    private int state;
    private String cardName1;
    private String cardName2;
    private String cardName3;
    private String cardName4;
    private String cardName5;
    private MultipartFile cardPic1;
    private MultipartFile cardPic2;
    private MultipartFile cardPic3;
    private MultipartFile cardPic4;
    private MultipartFile cardPic5;
    private int chance1;
    private int chance2;
    private int chance3;
    private int chance4;
    private int chance5;
}
