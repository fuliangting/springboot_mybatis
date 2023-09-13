package com.fu.springboot.controller;

import com.fu.springboot.exceptions.ParamsException;
import com.fu.springboot.po.User;
import com.fu.springboot.query.UserQuery;
import com.fu.springboot.service.UserService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * Controller
 * 表示控制器，类中的方法可以返回视图，也可以返回JSON数指如梁需要返回JSON数据，则需要在方法上设ResponseBodyRestcontroller
 * 相当于 @Controller + @ResponseBody，表示类中的方法统一返回JSON数据
 */
@Api(tags = "用户管理模块")
//@Controller
@RestController
public class UserController {
    @Resource
    private UserService userService;

    @GetMapping("user/{userName}")
    @ApiOperation(value = "根据用户名查询用户对象")
    @ApiImplicitParam(name = "userName", value = "用户名称", required = true, paramType = "path")
    //@ResponseBody
    public User queryUserByUserName(@PathVariable String userName) {
        System.out.println("测试热部署===0");
        return userService.queryUserByUserName(userName);
    }

    @GetMapping("user/id/{userId}")
    @Cacheable(value = "users", key = "#userId")
    //@ResponseBody
    @ApiOperation(value = "根据ID查询用户对象")
    @ApiImplicitParam(name = "userId", value = "用户ID", required = true, paramType = "path")
    public User queryUserByUserId(@PathVariable Integer userId) {
        return userService.queryUserByUserId(userId);
    }

    @PostMapping("user")
    @ApiOperation(value = "添加用户")
    @ApiImplicitParam(name = "user", value = "用户对象")
    public Map<String, Object> addUser(@RequestBody User user) {
        Map<String, Object> map = new HashMap<>();
/*
        try {
            userService.addUser(user);
            map.put("code", 200);
            map.put("msg", "添加成功");
        } catch (ParamsException ex) {
            map.put("code", ex.getCode());
            map.put("msg", ex.getMsg());
            ex.printStackTrace();
        } catch (Exception ex) {
            map.put("code", 500);
            map.put("msg", ex.getMessage());
            ex.printStackTrace();
        }
*/
        userService.addUser(user);
        map.put("code", 200);
        map.put("msg", "添加成功");

        return map;
    }

    @PutMapping("user")
    @ApiOperation(value = "修改用户")
    @ApiImplicitParam(name = "user", value = "用户对象")
    public Map<String, Object> updateUser(@RequestBody User user) {
        Map<String, Object> map = new HashMap<>();
        try {
            userService.updateUser(user);
            map.put("code", 200);
            map.put("msg", "修改成功");
        } catch (ParamsException ex) {
            map.put("code", ex.getCode());
            map.put("msg", ex.getMsg());
            ex.printStackTrace();
        } catch (Exception ex) {
            map.put("code", 500);
            map.put("msg", ex.getMessage());
            ex.printStackTrace();
        }
        return map;
    }

    @PutMapping("user03")
    @CachePut(value = "users", key = "#user.id")
    @ApiOperation(value = "修改用户")
    @ApiImplicitParam(name = "user", value = "用户对象")
    public User updateUser03(@RequestBody User user) {
        userService.updateUser(user);

        return user;
    }

    @DeleteMapping("user/id/{userId}")
    @Transactional(propagation = Propagation.REQUIRED)
    @CacheEvict(value = "users", key = "#userId")
    @ApiOperation(value = "删除用户")
    @ApiImplicitParam(name = "userId", value = "用户ID", required = true, paramType = "path")
    public Map<String, Object> deleteUser(@PathVariable Integer userId) {
        Map<String, Object> map = new HashMap<>();
        try {
            userService.deleteUser(userId);
            map.put("code", 200);
            map.put("msg", "删除成功");
        } catch (ParamsException ex) {
            map.put("code", ex.getCode());
            map.put("msg", ex.getMsg());
            ex.printStackTrace();
        } catch (Exception ex) {
            map.put("code", 500);
            map.put("msg", ex.getMessage());
            ex.printStackTrace();
        }
        return map;
    }

    @RequestMapping("list")
    @Cacheable(value = "users", key = "#userQuery.pageNum+'-'+#userQuery.pageSize+'-'+#userQuery.userName")
    @ApiOperation(value = "分页查询用户")
    @ApiImplicitParam(name = "userQuery", value = "用户查询对象")
    public PageInfo<User> queryUserByParam(UserQuery userQuery) {
        return userService.queryUserByParam(userQuery);
    }

    @PutMapping("user02")
    public Map<String, Object> updateUser02(@Valid User user) {
        Map<String, Object> map = new HashMap<>();

        userService.updateUser(user);
        map.put("code", 200);
        map.put("msg", "修改成功");

        return map;
    }
}
