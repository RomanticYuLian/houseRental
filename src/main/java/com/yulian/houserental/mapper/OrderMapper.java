package com.yulian.houserental.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yulian.houserental.entity.Orders;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface OrderMapper extends BaseMapper<Orders> {
    @Select("SELECT\n" +
            "orders.*\n" +
            "FROM\n" +
            "orders\n" +
            "WHERE\n" +
            "(orders.landlord_name " +
            "LIKE '%${query}%'"+
            "OR orders.lessee_name\n" +
            "LIKE '%${query}%')")
    IPage<Orders> getAll(Page page, @Param(value ="query" ) String query);
}
