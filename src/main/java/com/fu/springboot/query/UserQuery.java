package com.fu.springboot.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "用户查询类")
public class UserQuery {
    @ApiModelProperty(value = "当前页码")
    private Integer pageNum = 1;    //当前页码
    @ApiModelProperty(value = "每页条数")
    private Integer pageSize = 10;  //每页条数
    @ApiModelProperty(value = "用户姓名")
    private String userName;

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
