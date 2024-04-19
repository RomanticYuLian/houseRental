package com.yulian.houserental.service;

import com.yulian.houserental.entity.Feedback;
import com.yulian.houserental.utils.Mess;

public interface FeedbackService {
    Mess addMsg(String content,Integer id);

    Mess getMyFeedback(Integer id,Integer p, Integer size, String keyword);

    Mess getAllMsg(Integer p, Integer size, String keyword);

    Mess deleteMsg(Integer id);

}
