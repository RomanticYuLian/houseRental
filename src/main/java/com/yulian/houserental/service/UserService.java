package com.yulian.houserental.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.yulian.houserental.utils.Mess;
import com.yulian.houserental.entity.User;

public interface UserService extends IService<User> {
    Mess register(User user);

    Mess login(User user);

    Mess getInfo(Integer id);

    Mess queryUser(Integer p, Integer size, String keyword);

    Mess freezeUser(Integer id);

    Mess unFreezeUser(Integer id);

    Mess upHouse(Integer id);

    Mess downHouse(Integer id);

    Mess setMessage(User user, Integer id);

    Mess setPassword(String oldPassword, String newPassword, Integer id);

    Mess setHeadImage(Integer id, String imageUrl);
}
