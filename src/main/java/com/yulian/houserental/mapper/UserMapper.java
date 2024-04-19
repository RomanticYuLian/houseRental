package com.yulian.houserental.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yulian.houserental.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;


public interface UserMapper extends BaseMapper<User> {
    @Select("SELECT\n" +
            "user.*\n" +
            "FROM\n" +
            "user\n" +
            "WHERE\n" +
            "user.role != 0 AND\n" +
            "(user.username " +
            "LIKE '%${query}%'"+
            "OR user.telephone\n" +
            "LIKE '%${query}%')")
    IPage<User> queryUser(Page page,@Param(value ="query" ) String query);
}
