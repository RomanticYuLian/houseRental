package com.yulian.houserental.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yulian.houserental.entity.Feedback;
import com.yulian.houserental.entity.Orders;
import com.yulian.houserental.mapper.UserMapper;
import com.yulian.houserental.utils.Mess;
import com.yulian.houserental.entity.User;
import com.yulian.houserental.mapper.FeedbackMapper;
import com.yulian.houserental.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class FeedbackServiceImpl extends ServiceImpl<FeedbackMapper, Feedback> implements FeedbackService {

    @Autowired
    private FeedbackMapper feedbackMapper;
    @Autowired
    private UserMapper userMapper;

    @Override
    public Mess addMsg(String content, Integer id) {
        Feedback feedback = new Feedback();
        feedback.setSendTime(LocalDateTime.now());
        feedback.setUserId(id);
        User user = userMapper.selectById(id);
        feedback.setUsername(user.getUsername());
        if (content!=null){
            feedback.setContent(content);
            save(feedback);
            return Mess.success().mess("反馈成功");
        }else {
            return Mess.fail().mess("内容为空,失败");
        }

    }

    @Override
    public Mess getMyFeedback(Integer id,Integer p, Integer size, String keyword) {
        //获取个人发送的反馈记录
        QueryWrapper<Feedback> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", id);
        wrapper
                .and(e -> {
                    e
                            .like("content", keyword);
                });
        Page<Feedback> page = new Page<>(p, size);
        IPage<Feedback> iPage = page(page, wrapper);
        return Mess.success().data("page", iPage);
    }

    @Override
    public Mess getAllMsg(Integer p, Integer size, String keyword) {
        Page<Feedback> page = new Page<>(p, size);
        IPage<Feedback> iPage = feedbackMapper.getAllMsg(page, keyword);
        return Mess.success().data("page", iPage);
    }

    @Override
    public Mess deleteMsg(Integer id) {
        removeById(id);
        return Mess.success().mess("删除成功");
    }


}
