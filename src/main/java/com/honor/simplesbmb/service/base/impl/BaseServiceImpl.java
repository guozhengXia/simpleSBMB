package com.honor.simplesbmb.service.base.impl;

import com.honor.simplesbmb.dao.base.BaseDao;
import com.honor.simplesbmb.service.base.BaseService;

import java.lang.reflect.Field;
import java.util.List;

/**
 * Created by xiagz
 * Date:2018/9/10
 */
public abstract class BaseServiceImpl<T> implements BaseService<T> {
    //获取当前Dao
    protected abstract BaseDao<T> getBaseDao();

    @Override
    public int insert(T t) {
        return getBaseDao().insert(t);
    }

    @Override
    public int update(T t) {
        return getBaseDao().update(t);
    }

    /**
     * 保存逻辑
     * <p>
     * 经过反射找到id字段和id字段值。注意：对应数据库表的主键必须是id。
     * <p>
     * 使用id判断是否存在，存在则更新，不存在则插入
     *
     * @param t
     * @return 返回数据id
     */
    @Override
    public int save(T t) {
        int id = getIdValue(t);

        if (id > 0 && this.queryOne(id) != null) {
            this.update(t);//存在则更新
        } else {
            this.insert(t);//不存在则插入
            id = getIdValue(t);
        }
        return id;
    }

    /**
     * 根据对象得到id值
     * <p>
     * 经过反射找到id字段和id字段值。注意：对应数据库表的主键必须是id。
     *
     * @param t
     * @return
     */
    private int getIdValue(T t) {
        Field[] fields = t.getClass().getDeclaredFields();//通过反射得到所有字段。
        for (int j = 0; j < fields.length; j++) {//遍历所有字段，得到id字段
            String fieldName = fields[j].getName();
            if ("id".equals(fieldName)) {//如果该字段是id
                fields[j].setAccessible(true);//是private修饰的字段可见
                try {
                    Object value = fields[j].get(t);//获取字段值
                    if (value != null) {
                        return (Integer) value;
                    } else {
                        return 0;
                    }

                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                break;//找到id后则跳出for循环。
            }
        }
        return 0;
    }

    @Override
    public int delete(int id) {
        return getBaseDao().delete(id);
    }

    @Override
    public T queryOne(int id) {
        return getBaseDao().queryOne(id);
    }

    @Override
    public T queryOneByConditions(T t) {
        List<T> ts = this.queryList(t);
        if (ts == null || ts.size() == 0) {
            return null;
        }
        return ts.get(0);
    }

    @Override
    public List<T> queryList(T t) {
        return getBaseDao().queryList(t);
    }

    @Override
    public int queryCount(T t) {
        return getBaseDao().queryCount(t);
    }
}
