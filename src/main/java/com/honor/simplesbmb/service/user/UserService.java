package com.honor.simplesbmb.service.user;

import com.honor.simplesbmb.model.user.UserModel;
import com.honor.simplesbmb.service.base.BaseService;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by xiagz
 * Date:2018/9/11
 */
public interface UserService extends BaseService<UserModel> {

    /**
     * 注册
     * @param userModel
     * @return
     */
    int register(UserModel userModel);

    /**
     * 登录
     * @param response
     * @param userModel
     * @return
     */
    UserModel login(HttpServletResponse response, UserModel userModel);
}
