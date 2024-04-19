package com.yulian.houserental.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yulian.houserental.utils.Mess;
import com.yulian.houserental.entity.Orders;

public interface OrderService extends IService<Orders> {
    Mess getOrder(Integer id,Integer p, Integer size, String keyword);

    Mess getAll(Integer p, Integer size, String keyword);

    Mess addOrder(Integer houseId, Integer lessee);

    Mess removeOrder(Integer id);

    Mess payOrder(Integer id);

    Mess getOrderById(Integer id);
}
