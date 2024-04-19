package com.yulian.houserental;

import com.yulian.houserental.entity.User;
import com.yulian.houserental.mapper.UserMapper;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
@SpringBootTest
class HouseRentalApplicationTests {
    @Autowired
    private UserMapper userMapper;
    @Test
    public void testSelect() {
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);
    }
}
