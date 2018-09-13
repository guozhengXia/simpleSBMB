package com.honor.simplesbmb.service.token;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by xiagz
 * Date:2018/9/11
 */
public interface TokenService {

    /**
     * 只保存token，不指定有效期，默认有效期是2个小时
     *
     * @param jsonModel
     * @return
     */
    String generateToken(String jsonModel);

    /**
     * 保存token，自定义有效期
     *
     * @param jsonModel
     * @param expireTime
     * @return
     */
    String generateToken(String jsonModel, Long expireTime);

    /**
     * 判断token是否存在，没有存则不存在，过期也是不存在
     *
     * @param token
     * @return
     */
    boolean exists(String token);

    /**
     * 重置有效期
     * @param key
     * @param expireTime
     */
    void resetExpireTime(String key,long expireTime);



    /**
     * 根据token得到用户信息
     *
     * @param token
     * @return 返回json格式的用户对象
     */
    String getUserInfoByToken(String token);

    /**
     * 从request中获取用户信息，userinfo为空表示没有token或token过期。
     *
     * @param request
     * @return 返回所有用户信息
     */
    String getUserInfoByRequest(HttpServletRequest request);

    /**
     * 从request中获取用户信息，然后解析成用户名
     *
     * @param request
     * @return 返回管理员的name
     */
    String getNameByRequest(HttpServletRequest request);


    /**
     * 当用户退出登录时移除token
     *
     * @param token
     * @return
     */
    void removeToken(String token);
}
