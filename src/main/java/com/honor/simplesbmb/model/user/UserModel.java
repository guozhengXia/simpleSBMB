package com.honor.simplesbmb.model.user;

import com.fasterxml.jackson.annotation.JsonView;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;

/**
 * Created by xiagz
 * Date:2018/9/10
 *
 * 用户Model类
 */
public class UserModel {

    //指定返回给前端的字段
    public interface UserModelView{}

    //用户id
    @JsonView(UserModelView.class)
    private int id;
    //用户姓名
    @JsonView(UserModelView.class)
    private String name;
    //密码
    private String password;
    //用户手机号
    @JsonView(UserModelView.class)
    private String phoneNum;
    //用户年龄
    @JsonView(UserModelView.class)
    private int age;
    //用户注册时间
    @JsonView(UserModelView.class)
    private Date createdTime;
    //用户信息更新时间
    @JsonView(UserModelView.class)
    private Date updatedTime;


    //校验参数
    public String checkParam(){
        //校验username参数
        if (StringUtils.isEmpty(name)) {
            return "name";
        }
        //校验password参数
        if (StringUtils.isEmpty(password)) {
            return "password";
        }
        return null;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }


}
