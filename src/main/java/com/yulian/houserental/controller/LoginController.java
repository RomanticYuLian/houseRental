package com.yulian.houserental.controller;

import com.yulian.houserental.filter.GetId;
import com.yulian.houserental.utils.Mess;
import com.yulian.houserental.entity.User;
import com.yulian.houserental.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class LoginController {
    @Autowired
    UserService userService;

    @PostMapping("/register")
    public Mess Register(@RequestBody User user) {
        return userService.register(user);
    }

    @PostMapping("/login")
    public Mess Login(@RequestBody User user) {
        return userService.login(user);
    }

    @GetMapping("/getInfo")
    public Mess getInfo() {
        return userService.getInfo(GetId.id);
    }
}
