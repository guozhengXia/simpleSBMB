package com.honor.simplesbmb.service.base;

import java.util.List;

/**
 * Created by xiagz
 * Date:2018/9/10
 */
public interface BaseService<T> {

    /**
     * 新增
     */
    int insert(T t);


    /**
     * 更新
     */
    int update(T t);

    /**
     * 保存数据，首先根据id判断该数据是否存在，若不存在则插入，若存在则更新
     * @param t
     * @return
     */
    int save(T t);


    /**
     * 删除
     */
    int delete(int id);

    /**
     * 根据ID查询一个
     */
    T queryOne(int id);

    /**
     * 根据其他条件查询一个，此时可能存在多个，但只取第一个
     */
    T queryOneByConditions(T t);

    /**
     * 查询列表
     */
    List<T> queryList(T t);

    /**
     * 查询命中个数
     */
    int queryCount(T t);


}
