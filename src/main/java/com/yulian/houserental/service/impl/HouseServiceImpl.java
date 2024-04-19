package com.yulian.houserental.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yulian.houserental.entity.House;
import com.yulian.houserental.entity.User;
import com.yulian.houserental.mapper.HouseMapper;
import com.yulian.houserental.mapper.UserMapper;
import com.yulian.houserental.service.HouseService;
import com.yulian.houserental.utils.CheckUtil;
import com.yulian.houserental.utils.Mess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HouseServiceImpl extends ServiceImpl<HouseMapper, House> implements HouseService {

    @Autowired
    private HouseMapper houseMapper;
    @Autowired
    private UserMapper userMapper;

    @Override
    public Mess queryHouse(Integer p, Integer size, String keyword) {
        Page<House> page = new Page<>(p, size);
        IPage<House> houseIPage = houseMapper.queryHouse(page, keyword);
        return Mess.success().data("page", houseIPage);
    }

    //根据房屋id查询
    @Override
    public Mess queryHouseById(Integer id) {
        return Mess.success().data("house",getById(id));
    }

    @Override
    public Mess queryMyHouse(Integer id, Integer p, Integer size, String keyword) {
        Page<House> page = new Page<>(p, size);
        IPage<House> houseIPage = houseMapper.queryMyHouse(id,page,keyword);
        return Mess.success().data("page",houseIPage);
    }

    //--------------------房主---------------------

    @Override
    public Mess addHouse(House house, Integer landlordId) {
        if (house.getAddress() == null) {
            return Mess.fail().mess("添加失败,地址不能为空");
        } else if (house.getType() == null) {
            return Mess.fail().mess("添加失败,类型不能为空");
        } else if (house.getPrice() == null) {
            return Mess.fail().mess("添加失败,价格不能为空");
        } else if (!CheckUtil.checkPrice(house.getPrice())) {
            return Mess.fail().mess("添加失败,价格格式不合法");
        }
        User user = userMapper.selectById(landlordId);
        house.setUsername(user.getUsername());

        house.setLandlordId(landlordId);
        save(house);
        return Mess.success().mess("添加成功");
    }

    @Override
    public Mess updateHouse(House house) {
        if (house.getAddress() == null) {
            return Mess.fail().mess("修改失败,地址不能为空");
        } else if (house.getType() == null) {
            return Mess.fail().mess("修改失败,类型不能为空");
        } else if (house.getPrice() == null) {
            return Mess.fail().mess("修改失败,价格不能为空");
        } else if (!CheckUtil.checkPrice(house.getPrice())) {
            return Mess.fail().mess("修改失败,价格格式不合法");
        }
        updateById(house);
        return Mess.success().mess("修改成功");
    }

    @Override
    public Mess deleteHouse(Integer id) {
        removeById(id);
        return Mess.success().mess("删除成功");
    }
}
