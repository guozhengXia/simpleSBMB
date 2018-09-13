package com.honor.simplesbmb.redis;

/**
 * Created by xiagz
 * Date:2018/9/11
 *
 * 操作Redis的基本DAO
 */
public interface RedisDao {

    /**
     * 判断缓存中是否有对应的value
     *
     * @param key
     * @return
     */
    boolean exists(String key);

    /**
     * 写入缓存
     *
     * @param key
     * @param value
     * @return
     */
    boolean set(String key, Object value);

    /**
     * 写入缓存设置时效时间
     *
     * @param key
     * @param value
     * @param expireTime 单位是毫秒
     * @return
     */
    boolean set(String key, Object value, Long expireTime);

    /**
     * 读取缓存
     *
     * @param key
     * @return
     */
    Object get(String key);

    /**
     * 删除对应的value
     *
     * @param key
     * @return
     */
    boolean remove(String key);

    /**
     * 重置有效期
     * @param key
     * @param expireTime
     * @return
     */
    void resetExpireTime(String key, Long expireTime) ;

}
