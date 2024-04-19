package com.yulian.houserental.controller;

import com.yulian.houserental.utils.Mess;
import com.yulian.houserental.service.HouseService;
import com.yulian.houserental.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;
    @Autowired
    private HouseService houseService;

    @GetMapping("/queryUser/{p}/{size}")
    public Mess queryUser(@PathVariable(value = "p") Integer p, @PathVariable(value = "size") Integer size,String keyword) {
        return userService.queryUser(p, size, keyword);
    }

    @PostMapping("/freezeUser")
    public Mess freezeUser(Integer id) {
        return userService.freezeUser(id);
    }

    @PostMapping("/unfreezeUser")
    public Mess unFreezeUser(Integer id) {
        return userService.unFreezeUser(id);
    }

    @PostMapping("/freezeHouse")
    public Mess freezeHouse(Integer id) {
        return userService.downHouse(id);
    }

    @PostMapping("/unfreezeHouse")
    public Mess unfreezeHouse(Integer id) {
        return userService.upHouse(id);
    }

    @GetMapping("/queryHouse/{p}/{size}")
    public Mess queryHouse(@PathVariable(value = "p") Integer p, @PathVariable(value = "size") Integer size,@RequestBody String keyword) {
        return houseService.queryHouse(p, size, keyword);
    }

}
