package com.yulian.houserental.controller;

import com.yulian.houserental.entity.House;
import com.yulian.houserental.filter.GetId;
import com.yulian.houserental.service.HouseService;
import com.yulian.houserental.utils.Mess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private HouseService houseService;

    @GetMapping("/queryHouse/{p}/{size}")
    public Mess getHousePage(@PathVariable(value = "p") Integer p, @PathVariable(value = "size") Integer size, String keyword) {
        return houseService.queryHouse(p, size, keyword);
    }

    @GetMapping("/getHouseById")
    public Mess getHouseById(Integer id){
        return houseService.queryHouseById(id);
    }

    @GetMapping("/queryMyHouse/{p}/{size}")
    public Mess getMyHousePage(@PathVariable(value = "p") Integer p, @PathVariable(value = "size") Integer size, String keyword) {
        return houseService.queryMyHouse(GetId.id, p, size, keyword);
    }

    @PostMapping("/addHouse")
    public Mess addHouse(@RequestBody House house) {
        return houseService.addHouse(house, GetId.id);
    }

    @PostMapping("/updateHouse")
    public Mess update(@RequestBody House house) {
        return houseService.updateHouse(house);
    }

    @PostMapping("/deleteHouse")
    public Mess delete(Integer id) {
        return houseService.deleteHouse(id);
    }

}
