package com.yulian.houserental.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yulian.houserental.entity.House;
import com.yulian.houserental.utils.Mess;
import com.yulian.houserental.entity.User;
import com.yulian.houserental.mapper.HouseMapper;
import com.yulian.houserental.mapper.UserMapper;
import com.yulian.houserental.service.UserService;
import com.yulian.houserental.utils.CheckUtil;
import com.yulian.houserental.utils.JwtUtils;
import com.yulian.houserental.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;


@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private HouseMapper houseMapper;

    @Override
    public Mess register(User user) {
        //校验手机号格式是否正确
        if (!CheckUtil.checkPhone(user.getTelephone())) {
            return Mess.fail().mess("手机号格式错误");
        }
        /*
          定义两个QueryWrapper<>()对象，解决以下问题：
               在判断时，如果满足条件取得的用户名和手机号来自不同的两条记录，
               会产生多值冲突
               满足eq()方法的username来自------------>user1
               满足eq()方法的telephone来自----------->user2
         */
        QueryWrapper<User> wrapper1 = new QueryWrapper<>();
        QueryWrapper<User> wrapper2 = new QueryWrapper<>();
        //wrapper1中查询telephone
        wrapper1.eq("telephone", user.getTelephone());
        //wrapper2中查询username
        wrapper2.eq("username", user.getUsername());
        if (getOne(wrapper1) != null || getOne(wrapper2) != null) {
            return Mess.fail().mess("用户名或手机号已被注册");
        } else if (user.getRole()==null) {
            return Mess.fail().mess("角色未选择,注册失败");
        } else if (!CheckUtil.checkPassword(user.getPassword())) {
            return Mess.fail().mess("密码格式错误");
        } else {
            user.setCreateTime(LocalDate.now());
            user.setPassword(MD5Utils.stringToMD5(user.getPassword()));
            save(user);
            return Mess.success().mess("注册成功");
        }
    }

    @Override
    public Mess login(User user) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username", user.getUsername());
        User one = getOne(wrapper);
        if (one == null) {
            return Mess.fail().mess("用户不存在");
        } else if (one.getActive() == 0) {
            return Mess.fail().mess("该账户已被封禁");
        } else if (MD5Utils.stringToMD5(user.getPassword()).equals(one.getPassword())) {
            String token = JwtUtils.getJwtToken(one.getId(), one.getRole(), one.getUsername());
            one.setPassword(null);
            return Mess.success().data("token", token).data("user", one);
        } else {
            return Mess.fail().mess("密码错误");
        }
    }


    @Override
    public Mess getInfo(Integer id) {
        User user = getById(id);
        return Mess.success().data("user", user);
    }

    //--------------------管理员---------------------

    //查询用户
    @Override
    public Mess queryUser(Integer p, Integer size, String keyword) {
        Page<User> page = new Page<>(p, size);
        IPage<User> userIPage = userMapper.queryUser(page, keyword);
        return Mess.success().data("page", userIPage);
    }

    //封禁
    @Override
    public Mess freezeUser(Integer id) {
        UpdateWrapper<User> userWrapper = new UpdateWrapper<>();
        UpdateWrapper<House> houseWrapper = new UpdateWrapper<>();
        if (userMapper.selectById(id).getActive()==0){
            return Mess.fail().mess("该用户已经封禁!");
        }
        //用户封禁
        userWrapper.eq("id", id).setSql("active = 0");
        update(userWrapper);
        //房屋下架
        houseWrapper.eq("landlord_id", id).setSql("status = 0");
        houseMapper.update(houseWrapper);
        return Mess.success().mess("封禁成功");
    }

    //解封
    @Override
    public Mess unFreezeUser(Integer id) {
        UpdateWrapper<User> userWrapper = new UpdateWrapper<>();
        UpdateWrapper<House> houseWrapper = new UpdateWrapper<>();
        if (userMapper.selectById(id).getActive()==1){
            return Mess.fail().mess("该用户已经解封!");
        }
        userWrapper.eq("id", id).setSql("active = 1");
        update(userWrapper);
        houseWrapper.eq("landlord_id", id).setSql("status = 1");
        houseMapper.update(houseWrapper);
        return Mess.success().mess("解封成功");
    }

    @Override
    public Mess upHouse(Integer id) {
        UpdateWrapper<House> wrapper = new UpdateWrapper<>();
        //判断状态
        if(houseMapper.selectById(id).getStatus()==1){
            return Mess.fail().mess("该房屋状态已为上架!");
        }
        wrapper.setSql("status=1");
        houseMapper.update(wrapper);
        return Mess.success().mess("上架成功");
    }

    @Override
    public Mess downHouse(Integer id) {
        UpdateWrapper<House> wrapper = new UpdateWrapper<>();
        //判断状态
        if(houseMapper.selectById(id).getStatus()==0){
            return Mess.fail().mess("该房屋状态已为下架!");
        }
        wrapper.eq("id",id).setSql("status=0");
        houseMapper.update(wrapper);
        return Mess.success().mess("下架成功");
    }

    //--------------------信息修改---------------------
    @Override
    public Mess setMessage(User user, Integer id) {
        try {
            QueryWrapper<User> wrapper = new QueryWrapper<>();
            wrapper
                    .eq("username", user.getUsername())
                    .or().eq("telephone", user.getTelephone());
            User user1 = getOne(wrapper);
            //判断手机号或用户名是否已存在
            if (user1 == null || user1.getId().equals(id)) {
                user.setId(id);
                updateById(user);
                return Mess.success().mess("更新成功");
            } else if (user1.getUsername().equals(user.getUsername())) {
                return Mess.fail().mess("用户名已存在");
            } else if (!CheckUtil.checkPhone(user.getTelephone())) {
                return Mess.fail().mess("手机号格式错误");
            } else if (user1.getTelephone().equals(user.getTelephone())) {
                return Mess.fail().mess("手机号已存在");
            } else {
                return Mess.fail().mess("其他错误");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return Mess.fail().mess("用户信息已存在");
        }
    }

    @Override
    public Mess setPassword(String oldPassword, String newPassword, Integer id) {
        User user = getById(id);
        String old = MD5Utils.stringToMD5(oldPassword);
        if (user.getPassword().equals(old)) {
            if (!CheckUtil.checkPassword(newPassword)) {
                return Mess.fail().mess("新密码格式错误");
            }
            user.setPassword(MD5Utils.stringToMD5(newPassword));
            updateById(user);
            return Mess.success().mess("修改成功");
        }
        return Mess.fail().mess("旧密码错误");
    }

    @Override
    public Mess setHeadImage(Integer id, String imageUrl) {
        UpdateWrapper<User> wrapper = new UpdateWrapper<>();
        wrapper.eq("id", id).set("image_url", imageUrl);
        userMapper.update(wrapper);
        return update(wrapper) ? Mess.success().mess("更新成功") : Mess.fail().mess("更新失败");
    }
}

