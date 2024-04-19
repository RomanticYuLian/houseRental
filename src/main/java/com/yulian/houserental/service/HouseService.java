package com.yulian.houserental.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yulian.houserental.entity.House;
import com.yulian.houserental.utils.Mess;

public interface HouseService extends IService<House> {
    //分页查询
    Mess queryHouse(Integer p, Integer size, String keyword);

    Mess queryHouseById(Integer id);

    Mess queryMyHouse(Integer id,Integer p, Integer size, String keyword);

    Mess addHouse(House house, Integer landlordId);

    Mess updateHouse(House house);

    Mess deleteHouse(Integer id);
}
