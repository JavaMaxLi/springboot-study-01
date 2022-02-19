package com.lxf;

import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.lxf.common.s9010.S9010ManagerDBO;
import com.lxf.common.s9010.S9010ManagerDao;
import com.lxf.common.user.User;
import com.lxf.common.user.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;


@SpringBootTest
class SpringbootStudy01ApplicationTests {
    @Autowired
    UserMapper userMapper;

    @Test
    void contextLoads() {
        List<User> userList = userMapper.selectList(null);
        System.out.println(userList);
    }

    @Test
    void testInsert() {
        User user = new User();
        user.setName("xiaohua");
        user.setAge(20);
        user.setIdCard("22222222222222222222");
        user.setFromDatabase("spring_boot");
        int insert = userMapper.insert(user);
        System.out.println(insert);
    }

    public static void main(String[] args) throws IOException {


    }



}
