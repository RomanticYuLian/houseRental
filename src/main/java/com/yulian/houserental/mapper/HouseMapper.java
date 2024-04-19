package com.yulian.houserental.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yulian.houserental.entity.House;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface HouseMapper extends BaseMapper<House> {
    @Select("SELECT\n" +
            "house.*\n" +
            "FROM\n" +
            "house\n" +
            "WHERE\n" +
            "(house.address " +
            "LIKE '%${query}%'" +
            "OR house.type\n" +
            "LIKE '%${query}%'" +
            "OR house.username\n" +
            "LIKE '%${query}%')")
    IPage<House> queryHouse(Page page, @Param(value = "query") String query);

    @Select("SELECT\n" +
            "house.*\n" +
            "FROM\n" +
            "house\n" +
            "WHERE\n" +
            "(house.address " +
            "LIKE '%${query}%'" +
            "OR house.type\n" +
            "LIKE '%${query}%')" +
            "AND landlord_id=${id}"
    )
    IPage<House> queryMyHouse(Integer id, Page page, @Param(value = "query") String query);
}
