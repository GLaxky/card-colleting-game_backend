package com.kang.controller;

//import com.kang.pojo.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kang.pojo.vo.CompleteUserVo;
import com.kang.pojo.vo.SimpleUserVo;
import com.kang.service.UserService;
import com.kang.utils.JWTUtil;
import com.kang.utils.MsgCode;
import com.kang.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@RestController
@RequestMapping("/")
public class UserController {

    @Autowired
    @Qualifier("UserServiceImpl")
    private UserService userService;

    @PostMapping("/register")
    public Result<Object> register(CompleteUserVo user){
        System.out.println(user.toString());
        Date currentDate=new Date();
        user.setCreateTime(currentDate);
        MsgCode ans=userService.registerAUser(user);
        switch (ans){
            case REGISTER_USERNAME_EXISTED:
                return new Result<>(MsgCode.REGISTER_USERNAME_EXISTED, "用户名已存在", null);
            case REGISTER_SUCCESS:
                return new Result<>(MsgCode.REGISTER_SUCCESS, "注册成功", null);
            default:
                return new Result<>(MsgCode.OTHER_FAILED, "未知原因导致的错误", null);
        }

    }

    @PostMapping("/login")
    public Result<String> login(SimpleUserVo simpleUserVo){
//        System.out.println(simpleUserVo.toString());
        MsgCode ans= userService.loginCheck(simpleUserVo);
        if(ans==MsgCode.LOGIN_NO_ACCOUNT){
            return new Result<>(MsgCode.LOGIN_NO_ACCOUNT, "用户名错误", null);
        }else if(ans==MsgCode.LOGIN_UNMATCHED){
            return new Result<>(MsgCode.LOGIN_UNMATCHED, "用户名、密码不匹配", null);
        }else{
            CompleteUserVo user=userService.getUserInfoByName(simpleUserVo.getUserName());
            String token= JWTUtil.sign(user.getUserName(), user.getUserId(), user.getUserType());
            if(token!=null){
                return new Result<>(MsgCode.LOGIN_SUCCESS, "登录成功", token);
            }else{
                return new Result<>(MsgCode.LOGIN_SUCCESS, "token生成失败", null);
            }

        }
    }

    @GetMapping("/user/info")
    public Result<CompleteUserVo> getUserInfo(HttpServletRequest request){
        String token=request.getHeader("access-token");
        String userName=JWTUtil.getUsername(token);
        CompleteUserVo completeUserVo=userService.getUserInfoByName(userName);
        // todo-之后可以写个密码加密。。
        if (completeUserVo != null) {
            completeUserVo.setPassword(null);
            return new Result<>(MsgCode.OTHER_SUCCESS, "获取用户信息成功", completeUserVo);
        }else{
            return new Result<>(MsgCode.OTHER_FAILED, "获取用户信息出错", null);
        }
    }

    @PostMapping ("/user/infoOther")
    public Result<CompleteUserVo> getOtherUserInfo(String userId){
//        String token=request.getHeader("access-token");
//        String userName=JWTUtil.getUsername(token);
        CompleteUserVo completeUserVo=userService.getUserInfoById(userId);
        // todo-之后可以写个密码加密。。
        if (completeUserVo != null) {
            completeUserVo.setPassword(null);
            return new Result<>(MsgCode.OTHER_SUCCESS, "获取用户信息成功", completeUserVo);
        }else{
            return new Result<>(MsgCode.OTHER_FAILED, "获取用户信息出错", null);
        }
    }

    @PostMapping("/user/editTel")
    public Result<Object> editTel(HttpServletRequest request, String newTel) {
        String token=request.getHeader("access-token");
        String userName=JWTUtil.getUsername(token);
        CompleteUserVo completeUserVo=userService.getUserInfoByName(userName);
        completeUserVo.setTelephone(newTel);
        MsgCode ans= userService.editPersonalInfo(completeUserVo);
        return new Result<>(ans, "修改手机号成功", null);
    }

    @PostMapping("/user/editSex")
    public Result<Object> editSex(HttpServletRequest request, String newSex){
        String token=request.getHeader("access-token");
        String userName=JWTUtil.getUsername(token);
        CompleteUserVo completeUserVo=userService.getUserInfoByName(userName);
        completeUserVo.setSex(newSex);
        MsgCode ans= userService.editPersonalInfo(completeUserVo);
        return new Result<>(ans, "修改性别成功", null);
    }

    @PostMapping("/user/editNickName")
    public Result<Object> editNickName(HttpServletRequest request, String nickName){
        String token=request.getHeader("access-token");
        String userName=JWTUtil.getUsername(token);
        CompleteUserVo completeUserVo=userService.getUserInfoByName(userName);
        completeUserVo.setNickName(nickName);
        MsgCode ans= userService.editPersonalInfo(completeUserVo);
        return new Result<>(ans, "修改昵称成功", null);

    }

    @PostMapping("/user/editPW")
    public Result<Object> editPW(HttpServletRequest request, String PW, String newPW){
        String token=request.getHeader("access-token");
        String userName=JWTUtil.getUsername(token);
        CompleteUserVo completeUserVo=userService.getUserInfoByName(userName);
        if(!completeUserVo.getPassword().equals(PW)){
            return new Result<>(MsgCode.EDIT_PERMISSION, "原密码错误，修改密码失败", null);
        }
        if(completeUserVo.getPassword().equals(newPW)){
            return new Result<>(MsgCode.EDIT_PASSWORD_THE_SAME, "新密码应该与原密码不同", null);
        }
        completeUserVo.setPassword(newPW);
        MsgCode ans= userService.editPersonalInfo(completeUserVo);
        return new Result<>(ans, "修改密码成功", null);

    }
}
