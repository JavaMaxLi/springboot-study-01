package com.lxf;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.segments.MergeSegments;
import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lxf.common.s9010.S9010ManagerDBO;
import com.lxf.common.s9010.S9010ManagerDao;
import com.lxf.common.s9010.S9010ManagerService;
import com.lxf.common.user.User;
import com.lxf.common.user.UserMapper;
import com.lxf.common.userInfo.entity.UserInfo;
import com.lxf.common.userInfo.mapper.UserInfoMapper;
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
    @Autowired
    S9010ManagerDao s9010ManagerDao;
    @Autowired
    UserInfoMapper userInfoMapper;

    @Test
    void contextLoads() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.ge("age",20);
        wrapper.ge("version",2);
        wrapper.eq("name","huanhuan");
        List<User> userList = userMapper.selectList(wrapper);
        System.out.println(userList);
    }

    @Test
    void systemUser() {
        List<S9010ManagerDBO> s9010ManagerDBOS = s9010ManagerDao.doSelectList(new S9010ManagerDBO());
        System.out.println(s9010ManagerDBOS);
    }

    @Test
    void selectPage() {
        Page<User> page = new Page<>(2, 2);
        Page<User> userPage = userMapper.selectPage(page, null);
        System.out.println(userPage.getRecords());
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

    @Test
    void testInsertUserInfo() {
        UserInfo user = new UserInfo();
        user.setUserId(4);
        user.setAddress("杭州市江干区晨光国际");
        user.setEmail("1773937399@qq.com");
        user.setPhone("15968298211");
        int insert = userInfoMapper.insert(user);
        System.out.println(insert);
    }

    @Test
    void testUpdateUserInfo() {
        UserInfo user = new UserInfo();
        user.setPuk(1);
        user = userInfoMapper.selectById(user);
        user.setAddress("杭州市钱塘新区金沙国际");
        int i = userInfoMapper.updateById(user);
        System.out.println("修改条数："+i);
    }

    /**
     * 模拟多线程使用乐观锁更新数据
     * @throws InterruptedException
     */
    @Test
    void testUpdate() throws InterruptedException {
        User user = userMapper.selectById(13);
        for (int i = 0; i < 5; i++) {
            new Thread(() ->{
                user.setName("laogou");
                user.setAge(69);
                user.setIdCard("3321562264561216");
                user.setFromDatabase("spring_boot");
                int insert = userMapper.updateById(user);
                System.out.println(insert);
            }).start();
        }
        TimeUnit.SECONDS.sleep(2);
    }

    @Test
    void deleteUser(){
        int i = userMapper.deleteById(18);
        System.out.println("删除的数据条数："+i);
    }

    public static void main(String[] args) throws IOException {


    }



}
