package com.honor.simplesbmb.service.user.impl;

import com.alibaba.fastjson.JSONObject;
import com.honor.simplesbmb.dao.base.BaseDao;
import com.honor.simplesbmb.dao.user.UserDao;
import com.honor.simplesbmb.model.user.UserModel;
import com.honor.simplesbmb.service.base.impl.BaseServiceImpl;
import com.honor.simplesbmb.service.token.TokenService;
import com.honor.simplesbmb.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by xiagz
 * Date:2018/9/11
 *
 * User的Service
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<UserModel> implements UserService {

    @Autowired
    UserDao userDao;

    @Autowired
    TokenService tokenService;

    @Override
    protected BaseDao<UserModel> getBaseDao() {
        return userDao;
    }

    @Override
    public int register(UserModel userModel) {
        UserModel userModelTemp = new UserModel();
        userModelTemp.setName(userModel.getName());
        List<UserModel> userModelList = userDao.queryList(userModelTemp);
        if(userModelList != null && userModelList.size() > 0){
            return 1;//该用户已经存在
        }
        userModelTemp.setPassword(userModel.getPassword());
        userDao.insert(userModelTemp);
        return 2;//表示插入成功
    }

    @Override
    public UserModel login(HttpServletResponse response, UserModel userModel) {
        //注意：现在的查询条件只有nickname和password，但不能保证managerModel中只有这两个字段，所以需要创建新的ManagerModel对象。
        UserModel userModelTemp = new UserModel();
        userModelTemp.setName(userModel.getName());
        userModelTemp.setPassword(userModelTemp.getPassword());
        UserModel userModelNew = this.queryOneByConditions(userModelTemp);
        if(userModelNew == null){
            return null;
        }

        String jsonStr = JSONObject.toJSONString(userModelNew);
        String token = tokenService.generateToken(jsonStr);

        //登录成功，向客户端通过header的形式返回token值
        response.addHeader("Authorization", token);

        return userModelNew;
    }
}
