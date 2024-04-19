package com.yulian.houserental.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yulian.houserental.entity.House;
import com.yulian.houserental.entity.Orders;
import com.yulian.houserental.entity.User;
import com.yulian.houserental.mapper.HouseMapper;
import com.yulian.houserental.mapper.OrderMapper;
import com.yulian.houserental.mapper.UserMapper;
import com.yulian.houserental.service.OrderService;
import com.yulian.houserental.utils.Mess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Orders> implements OrderService {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private HouseMapper houseMapper;
    @Autowired
    private UserMapper userMapper;

    @Override
    public Mess getOrder(Integer id, Integer p, Integer size, String keyword) {
        QueryWrapper<Orders> wrapper = new QueryWrapper<>();
        User user = userMapper.selectById(id);
        Integer role = user.getRole();
        if (role == 1) {
            wrapper.eq("landlord_name", user.getUsername());
        } else if (role == 2) {
            wrapper.eq("lessee_name", user.getUsername());
        }

        wrapper
                .and(e -> {
                    e
                            .like("address", keyword)
                            .or()
                            .like("type", keyword);

                });
        //排序：按时间倒序
        wrapper.orderByDesc(true, "create_time");
        Page<Orders> page = new Page<>(p, size);
        IPage<Orders> iPage = page(page, wrapper);
        return Mess.success().data("page", iPage);//TODO--------可能存在问题
    }

    @Override
    public Mess getAll(Integer p, Integer size, String keyword) {
        Page<Orders> page = new Page<>(p, size);
        IPage<Orders> iPage = orderMapper.getAll(page, keyword);
        return Mess.success().data("page", iPage);
    }

    @Override
    public Mess addOrder(Integer houseId, Integer lesseeId) {
        QueryWrapper<Orders> wrapper = new QueryWrapper<>();
        wrapper.eq("lessee_id", lesseeId);
        wrapper.eq("house_id", houseId);

        if (houseMapper.selectById(houseId).getStatus() == 0) {
            return Mess.fail().mess("该房屋已下架,无法添加!");
        } else if (getOne(wrapper) != null) {
            return Mess.fail().mess("该房屋已添加,请勿重复添加!");
        }

        House house = houseMapper.selectById(houseId);
        Orders orders = new Orders();
        orders.setHouseId(houseId);
        orders.setAddress(house.getAddress());
        orders.setPrice(house.getPrice());
        orders.setType(house.getType());
        orders.setCreateTime(LocalDateTime.now());

        //设置房主姓名
        User landlord = userMapper.selectById(house.getLandlordId());
        orders.setLandlordName(landlord.getUsername());

        orders.setLesseeId(lesseeId);
        //设置租户姓名
        User lessee = userMapper.selectById(lesseeId);
        orders.setLesseeName(lessee.getUsername());

        orders.setImageUrl(house.getImageUrl());
        save(orders);
        return Mess.success().mess("添加成功");
    }

    @Override
    public Mess removeOrder(Integer id) {
        removeById(id);
        return Mess.success().mess("移除成功");
    }

    @Override
    public Mess payOrder(Integer id) {
        UpdateWrapper<Orders> wrapper1 = new UpdateWrapper<>();
        wrapper1.eq("id", id).setSql("status=1");
        update(wrapper1);
        return Mess.success().mess("支付成功");
    }

    //通过id找到订单,把信息显示出来进行支付
    @Override
    public Mess getOrderById(Integer id) {
        return Mess.success().data("order", getById(id));
    }

}
