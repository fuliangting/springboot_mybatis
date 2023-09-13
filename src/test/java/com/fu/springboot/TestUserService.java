package com.fu.springboot;

import com.fu.springboot.po.User;
import com.fu.springboot.service.UserService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Starter.class})
public class TestUserService {
    @Resource
    private UserService userService;

    @Before
    public void before() {
        System.out.println("单元测试前执行。。。");
    }

    @After
    public void after() {
        System.out.println("单元测试hou执行。。。");
    }

    @Test
    public void testQueryUserById() {
        User user = userService.queryUserByUserId(1);
        System.out.println(user.toString());
    }

    @Test
    public void testQueryUserByName() {
        User user = userService.queryUserByUserName("99");
        System.out.println(user.toString());
    }
}
