package com.duod.demoboot.controller;

import com.duod.demoboot.constant.ResultCode;
import com.duod.demoboot.domain.JsonDataDTO;
import com.duod.demoboot.domain.UserQuery;
import com.duod.demoboot.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/pub")
public class PublicController {

    @Autowired
    private UserService userService;

    @RequestMapping("need_login")
    public JsonDataDTO needLogin(){
        return JsonDataDTO.buildSuccess(ResultCode.NEED_LOGIN);
    }

    @RequestMapping("not_permit")
    public JsonDataDTO notPermit(){
        return JsonDataDTO.buildSuccess(ResultCode.NOT_PERMIT);
    }

    @RequestMapping("index")
    public JsonDataDTO index(){
        List<String> videoList = new ArrayList<>();
        videoList.add("Mysql零基础到热门,数据库实战1");
        videoList.add("Mysql零基础到热门,数据库实战2");
        videoList.add("Mysql零基础到热门,数据库实战3");
        videoList.add("Redis高并发高可用集群百万级秒杀实战1");
        videoList.add("Redis高并发高可用集群百万级秒杀实战2");
        videoList.add("Redis高并发高可用集群百万级秒杀实战3");
        return JsonDataDTO.buildSuccess(videoList);
    }

    @PostMapping(value = "login")
    public JsonDataDTO login(@RequestBody UserQuery userQuery, HttpServletRequest request,
                             HttpServletResponse response){

        Subject subject = SecurityUtils.getSubject();

        try {
            UsernamePasswordToken usernamePasswordToken =
                    new UsernamePasswordToken(userQuery.getUsername(),userQuery.getPassword());
            subject.login(usernamePasswordToken);

            return JsonDataDTO.buildSuccess(subject.getSession().getId());

        }catch (Exception e){
            e.printStackTrace();
            return JsonDataDTO.buildError(ResultCode.ACCOUNT_OR_PASSWORD_FAILED);
        }

    }


}
