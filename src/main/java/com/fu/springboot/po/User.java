package com.fu.springboot.po;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;
import sun.awt.SunHints;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

//实现序列化
@ApiModel(value = "用户实体类")
public class User implements Serializable {
    //@ApiModelProperty(name = "id", value = "用户ID", example = "0")
    private Integer id;

    @NotBlank(message = "用户姓名不能为空~~~")
    @ApiModelProperty(value = "用户姓名")
    private String userName;

    @NotBlank(message = "用户密码不能为空~~~")
    @Length(min=4, max=10, message = "密码长度在4-10之间")
    //@ApiModelProperty(value = "用户密码")
    private String userPwd;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", userPwd='" + userPwd + '\'' +
                '}';
    }
}
