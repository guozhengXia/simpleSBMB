package com.honor.simplesbmb.filter;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by xiagz
 * Date:2018/9/11
 *
 * 对于跨域的请求，Web前端总是先发送一个预请求，预请求是options方式，对这个请求方式进行拦截，做特殊处理。
 */
public class OptionsCorsFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest)servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse)servletResponse;


        //设置允许的Origin请求,origin即是前端项目所在的服务的协议，ip及端口号。
        String oneOrigin = getOneOrigin(httpServletRequest);
        if(StringUtils.isNotEmpty(oneOrigin)){
            httpServletResponse.setHeader("Access-Control-Allow-Origin",oneOrigin);
        }
        //允许携带cookie
        httpServletResponse.setHeader("Access-Control-Allow-Credentials","true");
        //设置允许展示哪些响应头，多个响应头用英文逗号链接
        httpServletResponse.setHeader("Access-Control-Expose-Headers",
                "access-control-allow-headers,access-control-allow-methods,access-control-allow-origin, Authorization");


        String method = httpServletRequest.getMethod();
        if("OPTIONS".equals(method)){//如果是OPTIONS请求则拦截，添加响应头后直接返回
            //设置允许在请求头中设置content-type，
            //此设置只放在OPTIONS请求方式中即可，其他请求方式系统会自动处理，如果此时处理了其他请求方式，会覆盖调系统处理方案
            httpServletResponse.setHeader("Access-Control-Allow-Headers","content-type,Authorization");
        }else {//如果不是OPTIONS请求则放行
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        }

    }

    @Override
    public void destroy() {}


    /**
     * 从httpServletRequest获取传递过来的Origin
     *
     * @param httpServletRequest
     * @return
     */
    private String getOneOrigin(HttpServletRequest httpServletRequest){
        String origin = httpServletRequest.getHeader("Origin");
        if("http://52.80.99.236:82".equals(origin)){//测试环境前端服务器
            return origin;
        }else if("http://52.80.99.236:83".equals(origin)){//正式环境前端服务器
            return origin;
        }else {
            return null;
        }
    }
}
