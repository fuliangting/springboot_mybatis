package com.fu.springboot.service;

import com.fu.springboot.dao.UserMapper;
import com.fu.springboot.po.User;
import com.fu.springboot.query.UserQuery;
import com.fu.springboot.util.AssertUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiModel;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class UserService {
    @Resource
    private UserMapper userMapper;

    public User queryUserByUserName(String userName) {
        return userMapper.queryUserByUserName(userName);
    }

    public User queryUserByUserId(Integer userId) {
        return userMapper.queryUserByUserId(userId);
    }

    /**
     * 1. 参数校验 (用户名与密码，非空)
     * 2. 调用Dao 层的添加方法，返回受影啊的行数
     * 3. 判断受影响的行数
     *
     * @param user
     */
    public void addUser(User user) {
        AssertUtil.isTrue(StringUtils.isBlank(user.getUserName()), "用户姓名不能为空");
        AssertUtil.isTrue(StringUtils.isBlank(user.getUserPwd()), "用户密码不能为空");
        AssertUtil.isTrue(queryUserByUserName(user.getUserName()) != null, "用户已存在");
        AssertUtil.isTrue(userMapper.addUser(user) <= 0, "添加用户失败");
    }

    public void updateUser(User user) {
        AssertUtil.isTrue(StringUtils.isBlank(user.getUserName()), "用户姓名不能为空");
        AssertUtil.isTrue(StringUtils.isBlank(user.getUserPwd()), "用户密码不能为空");
        AssertUtil.isTrue(user.getId() == null, "用户编号不是数字");
        User tmp = queryUserByUserId(user.getId());
        AssertUtil.isTrue(tmp == null, "用户编号不存在");
        if (!tmp.getUserName().equals(user.getUserName())) {
            AssertUtil.isTrue(queryUserByUserName(user.getUserName()) != null, "用户已存在");
        }
        AssertUtil.isTrue(userMapper.updateUser(user) <= 0, "修改用户失败");
    }

    public void deleteUser(Integer userId) {
        AssertUtil.isTrue(queryUserByUserId(userId) == null, "用户不存在");
        AssertUtil.isTrue(userMapper.deleteUser(userId) <= 0, "修改用户失败");
    }

    public PageInfo<User> queryUserByParam(UserQuery userQuery) {
        PageHelper.startPage(userQuery.getPageNum(), userQuery.getPageSize());

        List<User> userList =  userMapper.queryUserByParam(userQuery);

        PageInfo<User> userPageInfo = new PageInfo<>(userList);

        return userPageInfo;
    }
}
