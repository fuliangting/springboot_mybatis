package com.fu.springboot.dao;

import com.fu.springboot.po.User;
import com.fu.springboot.query.UserQuery;

import java.util.List;
import java.util.Map;

public interface UserMapper {
    // 根据用户名查询用户记录
    public User queryUserByUserName(String userName);

    // 根据用户ID查询用户记录
    public User queryUserByUserId(Integer userId);

    //增加用户
    public int addUser(User user);

    //修改用户
    public int updateUser(User user);

    //删除用户
    public int deleteUser(Integer userId);

    //查询用户
    public List<User> queryUserByParam(UserQuery userQuery);
}
