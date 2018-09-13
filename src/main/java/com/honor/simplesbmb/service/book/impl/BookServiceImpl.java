package com.honor.simplesbmb.service.book.impl;

import com.honor.simplesbmb.dao.base.BaseDao;
import com.honor.simplesbmb.dao.book.BookDao;
import com.honor.simplesbmb.model.book.BookModel;
import com.honor.simplesbmb.service.base.impl.BaseServiceImpl;
import com.honor.simplesbmb.service.book.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by xiagz
 * Date:2018/9/11
 */
@Service
public class BookServiceImpl extends BaseServiceImpl<BookModel> implements BookService {

    @Autowired
    BookDao bookDao;
    @Override
    protected BaseDao<BookModel> getBaseDao() {
        return bookDao;
    }

}
