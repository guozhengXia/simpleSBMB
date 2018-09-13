package com.honor.simplesbmb.dao.base;

import java.util.List;

/**
 * Created by xiagz
 * Date:2018/9/10
 * <p>
 * Dao的基础接口，任何dao接口都要继承该接口
 */
public interface BaseDao<T> {

    /**
     * 新增
     */
    int insert(T t);

    /**
     * 根据id删除
     */
    int delete(int id);

    /**
     * 根据id更新
     */
    int update(T t);


    /**
     * 根据id查询一个
     */
    T queryOne(int id);

    /**
     * 查询列表
     */
    List<T> queryList(T t);

    /**
     * 查询命中个数
     */
    int queryCount(T t);


}
