package com.kang.interceptor;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kang.utils.JWTUtil;
import com.kang.utils.MsgCode;
import com.kang.utils.Result;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class TokenInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
        response.setCharacterEncoding("utf-8");
        String token = request.getHeader("access-token");
        //token不存在
        if (null != token) {
            //验证token是否正确
            boolean result = JWTUtil.verify(token);
            if (result) {
                return true;
            }
            responseMessage(response,response.getWriter(), new Result<>(MsgCode.TOKEN_WRONG, "Token不正确", null));
            return false;
        }
        responseMessage(response,response.getWriter(), new Result<>(MsgCode.TOKEN_EXPIRED_OR_NOT_EXISTS, "Token不存在或过期", null));
        return false;
    }

//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
//
//    }
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
//
//    }

    /**
     * 返回(错误)信息给客户端
     *
     * @param response
     * @param out
     * @param result
     */
    private void responseMessage(HttpServletResponse response, PrintWriter out, Result<Object> result) {
        response.setContentType("application/json; charset=utf-8");
        result.setData(null);
        try{
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(result);
            out.print(json);
            out.flush();
            out.close();
        }catch (JsonProcessingException e){
            e.printStackTrace();
        }


    }
}
