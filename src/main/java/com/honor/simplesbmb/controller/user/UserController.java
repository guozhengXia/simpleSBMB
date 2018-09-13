package com.honor.simplesbmb.controller.user;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonView;
import com.honor.simplesbmb.model.user.UserModel;
import com.honor.simplesbmb.service.user.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by xiagz
 * Date:2018/9/11
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    /**
     * 注册接口
     *
     * @param userModel
     * @return
     */
    @RequestMapping(value = "register.do", method = RequestMethod.POST)
    public Object register(@RequestBody UserModel userModel) {
        JSONObject jsonObject = new JSONObject();
        //校验参数
        String checkParam = userModel.checkParam();
        if (StringUtils.isNotEmpty(checkParam)) {
            jsonObject.put("code", 201);
            jsonObject.put("msg", checkParam + "不能为空");
        }
        //注册
        int result = userService.register(userModel);
        switch (result) {
            case 1://该用户已经存在
                jsonObject.put("code", 401);
                jsonObject.put("msg", "该用户已经存在");
                break;
            case 2:
                jsonObject.put("code", 200);
                jsonObject.put("msg", "success");
                break;
        }
        return jsonObject;
    }


    /**
     * 登录接口
     *
     * @param response
     * @param managerModel
     * @return
     */
    @RequestMapping(value = "login.do", method = RequestMethod.POST)
    @JsonView(UserModel.UserModelView.class)
    public Object login(HttpServletResponse response, @RequestBody UserModel managerModel) {

        JSONObject jsonObject = new JSONObject();
        //校验参数
        String checkParam = managerModel.checkParam();
        if (StringUtils.isNotEmpty(checkParam)) {
            jsonObject.put("code", 201);
            jsonObject.put("msg", checkParam + "不能为空");
            return jsonObject;
        }

        UserModel managerModel1 = userService.login(response, managerModel);
        if (managerModel1 == null) {
            jsonObject.put("code", 400);
            jsonObject.put("msg", "账号或密码错误");
        } else {
            jsonObject.put("code", 200);
            jsonObject.put("msg", "success");
            jsonObject.put("data", managerModel1);
        }
        return jsonObject;
    }
}
