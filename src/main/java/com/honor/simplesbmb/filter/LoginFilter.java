package com.honor.simplesbmb.filter;

import com.alibaba.fastjson.JSONObject;
import com.honor.simplesbmb.service.token.TokenService;
import com.honor.simplesbmb.service.token.impl.TokenServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by xiagz
 * Date:2018/9/11
 *
 * 登录Filter
 */
public class LoginFilter implements Filter {

    @Autowired
    TokenService tokenService;

    /**
     * 不需要验证登录token的接口
     */
    private String[] IGNORE_URI = {
            "/user/register.do",//注册接口
            "/user/login.do",//登录接口
            "/front/test.do",//测试接口
    };
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest httpServletRequest = ((HttpServletRequest)servletRequest);
        HttpServletResponse httpServletResponse = ((HttpServletResponse)servletResponse);
        //获取包括域名的全路径
        String url = httpServletRequest.getRequestURL().toString();

        //获取接口的value，不包括域名和端口号
        String uri = httpServletRequest.getRequestURI();
        if(contain(uri)){//如果包含在忽略uri中则不做处理
            filterChain.doFilter(servletRequest,servletResponse);
        }else {//验证是否登录
            String token = httpServletRequest.getHeader("Authorization");
            if (token != null && token.startsWith("Bearer ")) {//如果token正确
                boolean exists = tokenService.exists(token);
                if(exists){//如果token存在则放行
                    //重置token的有效期
                    tokenService.resetExpireTime(token, TokenServiceImpl.MANAGER_TOKEN_OUT_TIME);
                    filterChain.doFilter(servletRequest,servletResponse);
                }else {//token不存在或失效
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("code",301);
                    jsonObject.put("msg","token失效，请重新登录");
                    httpServletResponse.setContentType("text/html;charset=UTF-8");
                    httpServletResponse.getWriter().write(jsonObject.toJSONString());
                }
            }else {//没有token
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("code",201);
                jsonObject.put("msg","请输入有效的token");
                httpServletResponse.setContentType("text/html;charset=UTF-8");
                httpServletResponse.getWriter().write(jsonObject.toJSONString());
            }
        }
    }

    /**
     * 判断该uri是否存在于IGNORE_URI中
     * @param uri
     * @return
     */
    private boolean contain(String uri) {
        for (String str : IGNORE_URI){
            if(str.equals(uri)){//如果与某个元素相同则返回true
                return true;
            }
        }
        return false;
    }

    @Override
    public void destroy() {

    }
}
