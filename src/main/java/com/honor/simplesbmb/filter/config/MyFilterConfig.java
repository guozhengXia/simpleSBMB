package com.honor.simplesbmb.filter.config;

import com.honor.simplesbmb.filter.LoginFilter;
import com.honor.simplesbmb.filter.OptionsCorsFilter;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

/**
 * Created by xiagz
 * Date:2018/9/11
 * 注入Filter的bean
 * <p>
 * 注意order的参数值，表示filter的优先级，order越小优先级越高。
 * 但我们自定义的filter不能优先于系统的filter，所以要把order值设置较大。
 * 暂时规定登录的优先级为Integer.MAX_VALUE - 100，其他以这个为准则，优先级高于登录则小于Integer.MAX_VALUE - 100
 */
@SpringBootApplication
public class MyFilterConfig {


    /**
     * 定义LoginFilter的bean
     *
     * @return
     */
    @Bean
    public LoginFilter myLoginFilter() {
        return new LoginFilter();
    }

    /**
     * 定义 OptionsCorsFilter 的bean
     *
     * @return
     */
    @Bean
    public OptionsCorsFilter myOptionsCorsFilter() {
        return new OptionsCorsFilter();
    }


    /**
     * 设置OptionsCorsFilter的urlPatterns和优先级
     *
     * 这个要放到登录之前
     * @return
     */
    @Bean
    public FilterRegistrationBean optionsCorsFilterRegistrationBean() {
        FilterRegistrationBean filterBean = new FilterRegistrationBean();
        filterBean.setFilter(myOptionsCorsFilter());
        filterBean.addUrlPatterns("/*");
        filterBean.setOrder(Integer.MAX_VALUE - 100 - 1);//order的数值越小 则优先级越高
        return filterBean;
    }
//
//

    /**
     * 设置LoginFilter的urlPatterns和优先级
     *
     * @return
     */
    @Bean
    public FilterRegistrationBean loginFilterRegistrationBean() {
        FilterRegistrationBean filterBean = new FilterRegistrationBean();
        filterBean.setFilter(myLoginFilter());
        filterBean.addUrlPatterns("/*");
        filterBean.setOrder(Integer.MAX_VALUE - 100);//order的数值越小 则优先级越高
        return filterBean;
    }
}
