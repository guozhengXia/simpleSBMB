package com.honor.simplesbmb.model.book;

import java.util.Date;

/**
 * Created by xiagz
 * Date:2018/9/11
 */
public class BookModel {
    //书id
    private int id;
    //书名
    private String title;
    //书描述
    private String bookDesc;
    //书的价格
    private String price;
    //数据加入时间
    private Date createdTime;
    //数据更新时间
    private Date updatedTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBookDesc() {
        return bookDesc;
    }

    public void setBookDesc(String bookDesc) {
        this.bookDesc = bookDesc;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }
}
