package com.yulian.houserental.controller;

import com.yulian.houserental.entity.User;
import com.yulian.houserental.filter.GetId;
import com.yulian.houserental.service.UserService;
import com.yulian.houserental.utils.Mess;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/setting")
public class SettingController {
    @Autowired
    private UserService userService;

    @PostMapping("/setMessage")
    public Mess setMessage(@RequestBody User user) {
        return userService.setMessage(user, GetId.id);
    }

    @PostMapping("/setPassword")//TODO--------请求
    public Mess setPassword(String oldPassword, String newPassword) {
        return userService.setPassword(oldPassword, newPassword, GetId.id);
    }

    @PostMapping("/setHeadImage")
    public Mess setHeadImage(String imageUrl) {
        return userService.setHeadImage(GetId.id, imageUrl);
    }
}
