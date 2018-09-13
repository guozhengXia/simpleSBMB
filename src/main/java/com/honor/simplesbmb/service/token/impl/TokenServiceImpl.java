package com.honor.simplesbmb.service.token.impl;

import com.alibaba.fastjson.JSONObject;
import com.honor.simplesbmb.model.user.UserModel;
import com.honor.simplesbmb.redis.RedisDao;
import com.honor.simplesbmb.service.token.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

/**
 * Created by xiagz
 * Date:2018/9/11
 */
@Service
public class TokenServiceImpl implements TokenService {

    @Autowired
    RedisDao redisDao;

    /**
     * token有效期，默认2个小时
     */
    public static final long MANAGER_TOKEN_OUT_TIME = 1000L * 60 * 60 * 2;


    /**
     * 生成且保存token，不指定有效期，默认有效期是2个小时
     *
     * @param jsonModel 由UserModel转化成的jsonObject字符串
     * @return
     */
    public String generateToken(String jsonModel) {
        return generateToken(jsonModel, MANAGER_TOKEN_OUT_TIME);
    }


    /**
     * 保存token，自定义有效期
     *
     * @param jsonModel
     * @param expireTime
     * @return
     */
    public String generateToken(String jsonModel, Long expireTime) {
        String token = "Bearer " + UUID.randomUUID().toString() + "_" + DigestUtils.md5DigestAsHex((jsonModel).getBytes()) + "_" + System.currentTimeMillis();
        redisDao.set(token, jsonModel, expireTime);
        return token;
    }


    /**
     * 判断token是否存在，没有存则不存在，过期也是不存在.
     * <p>
     * 如果存在则重置失效期
     *
     * @param token
     * @return
     */
    public boolean exists(String token) {
        return redisDao.exists(token);
    }

    /**
     * 重置token有效期，访问一此重置一次有效期
     *
     * @param token
     * @param expireTime
     */
    public void resetExpireTime(String token, long expireTime) {
        redisDao.resetExpireTime(token, expireTime);
    }

    /**
     * 根据token得到用户信息
     *
     * @param token
     * @return 返回json格式的用户对象，可以转化成UserModel对象
     */
    public String getUserInfoByToken(String token) {
        Object object = redisDao.get(token);
        if (object != null) {
            return (String) object;
        }
        return null;
    }

    /**
     * 从request中获取用户信息，userinfo为空表示没有token或token过期。
     *
     * @param request
     * @return
     */
    public String getUserInfoByRequest(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token == null) {
            return null;
        }
        return getUserInfoByToken(token);
    }

    @Override
    public String getNameByRequest(HttpServletRequest request) {
        String userInfoByRequest = getUserInfoByRequest(request);
        if (userInfoByRequest == null) {
            return null;
        }
        UserModel userModel = JSONObject.parseObject(userInfoByRequest, UserModel.class);
        return userModel.getName();
    }


    /**
     * 当用户退出登录时移除token
     *
     * @param token
     * @return
     */
    public void removeToken(String token) {
        if (exists(token)) {
            redisDao.remove(token);
        }
    }

}
