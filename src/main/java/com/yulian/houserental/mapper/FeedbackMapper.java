package com.yulian.houserental.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yulian.houserental.entity.Feedback;
import com.yulian.houserental.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface FeedbackMapper extends BaseMapper<Feedback> {
    @Select("SELECT\n" +
            "feedback.*\n" +
            "FROM\n" +
            "feedback\n" +
            "WHERE\n" +
            "(feedback.username " +
            "LIKE '%${query}%'"+
            "OR feedback.content\n" +
            "LIKE '%${query}%')")
    IPage<Feedback> getAllMsg(Page page, @Param(value ="query" ) String query);
}
