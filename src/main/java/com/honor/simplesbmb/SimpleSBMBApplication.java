package com.honor.simplesbmb;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by xiagz
 * Date:2018/9/10
 */
@SpringBootApplication
@MapperScan("com.honor.simplesbmb.dao")
public class SimpleSBMBApplication {

    public static void main(String[] args){
        SpringApplication.run(SimpleSBMBApplication.class,args);
    }
}
