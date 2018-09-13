package com.honor.simplesbmb.dao.user;

import com.honor.simplesbmb.dao.base.BaseDao;
import com.honor.simplesbmb.model.user.UserModel;
import org.springframework.stereotype.Repository;

/**
 * Created by xiagz
 * Date:2018/9/10
 *
 * 用户dao
 * 由于该接口继承BaseDao，所以自动具有增删该查等基本操作sql的方法
 */
@Repository
public interface UserDao extends BaseDao<UserModel> {

}
