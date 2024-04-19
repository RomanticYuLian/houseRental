package com.yulian.houserental.controller;


import com.yulian.houserental.filter.GetId;
import com.yulian.houserental.utils.Mess;
import com.yulian.houserental.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/getMyOrder/{p}/{size}")
    public Mess getMyOrder(@PathVariable(value = "p") Integer p, @PathVariable(value = "size") Integer size, String keyword) {
        return orderService.getOrder(GetId.id, p, size, keyword);
    }

    @PostMapping("/addOrder")
    public Mess addOrder(Integer houseId) {
        return orderService.addOrder(houseId, GetId.id);
    }

    @GetMapping("/getAll/{p}/{size}")
    public Mess getAllOrder(@PathVariable(value = "p") Integer p, @PathVariable(value = "size") Integer size, String keyword) {
        return orderService.getAll(p, size, keyword);
    }

    @PostMapping("/removeOrder")
    public Mess removeOrder(Integer id) {
        return orderService.removeOrder(id);
    }

    @PostMapping("/payOrder")
    public Mess payOrder(Integer id) {
        return orderService.payOrder(id);
    }

    @GetMapping("/getOrderDetails")
    public Mess getOrderDetails(Integer id){
        return orderService.getOrderById(id);
    }
}
