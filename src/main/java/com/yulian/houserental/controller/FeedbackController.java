package com.yulian.houserental.controller;

import com.yulian.houserental.entity.Feedback;
import com.yulian.houserental.filter.GetId;
import com.yulian.houserental.utils.Mess;
import com.yulian.houserental.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/feedback")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @PostMapping("/addFeedback")
    public Mess addMsg(String content) {
        return feedbackService.addMsg(content, GetId.id);
    }

    @GetMapping("/getMyFeedback/{p}/{size}")
    public Mess getMyFeedback(@PathVariable(value = "p") Integer p, @PathVariable(value = "size") Integer size, String keyword) {
        return feedbackService.getMyFeedback(GetId.id, p, size, keyword);
    }

    @GetMapping("/getAllMsg/{p}/{size}")
    public Mess getAllMsg(@PathVariable(value = "p") Integer p, @PathVariable(value = "size") Integer size, String keyword) {
        return feedbackService.getAllMsg(p, size, keyword);
    }

    @PostMapping("/removeFeedback")
    public Mess removeFeedback(Integer id) {
        return feedbackService.deleteMsg(id);
    }
}
