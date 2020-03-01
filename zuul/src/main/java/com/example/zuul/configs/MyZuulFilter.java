package com.example.zuul.configs;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletRequest;

/**
 * 自定义Zuul过滤器
 *
 * @author Sean
 * 2020/03/01
 */
@Configuration
public class MyZuulFilter extends ZuulFilter {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 过滤时机
     * pre:请求之前
     * route:处理请求进行路由
     * post:请求之后
     * error:出现错误
     *
     * @return 过滤时机
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * 过滤器优先级（越小越高）
     *
     * @return 优先级
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * serialNumber存在时启用过滤
     *
     * @return 是否过滤
     */
    @Override
    public boolean shouldFilter() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        String serialNumber = request.getParameter("serialNumber");
        return !StringUtils.isEmpty(serialNumber);
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        String serialNumber = request.getParameter("serialNumber");
        String verificationCode = request.getParameter("verificationCode");
        //从redis取出验证码比较
        String veriCode = stringRedisTemplate.opsForValue().get(serialNumber);
        if (veriCode == null || !verificationCode.equals(veriCode)) {
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(401);
            ctx.getResponse().setContentType(MediaType.APPLICATION_JSON_UTF8.getType());
            ctx.setResponseBody("{'success':false,'message':'Verification Code Error'}");
        }
        return null;
    }
}
