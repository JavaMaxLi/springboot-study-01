package com.lxf.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author LiXiaoFeng
 * @date 2022年01月13日 10:06
 */
//@Configuration
public class MyViewController implements WebMvcConfigurer {
   /* @Override
    public void addViewControllers(ViewControllerRegistry registry) {
       // registry.addRedirectViewController("/","//");
        registry.addViewController("/index.html").setViewName("index");
        registry.addViewController("/").setViewName("login");
    }
*/
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new HandlerInterceptor() {
            @Override
            public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
                request.setAttribute("_request_start_time_", System.currentTimeMillis());
             /*   if ("/".equals(request.getRequestURI()) || "/user/login".equals(request.getRequestURI())){

                    return true;
                    *//*response.sendRedirect("/errorPage");
                    return false;*//*
                }*/
                if (request.getSession().getAttribute("userAccount") == "" || request.getSession().getAttribute("userAccount") == null) {
                    System.out.println("没有权限，请先登录");
                    request.setAttribute("msg","没有权限，请先登录");
                    request.getRequestDispatcher("/").forward(request,response);
                    return false;
                } else {
                    System.out.println("userAccount"+request.getSession().getAttribute("userAccount"));
                    //response.sendRedirect(request.getRequestURI());
                    return true;
                }

            }

            @Override
            public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
                if (handler instanceof HandlerMethod) {
                    try {
                        // 计算
                        long startTime = (Long) request.getAttribute("_request_start_time_");
                        long endTime = System.currentTimeMillis();
                        long executeTime = endTime - startTime;
                        System.out.println("=====请求路径=====>>>>>" + request.getRequestURL().toString());
                        //System.out.println("=====请求参数=====>>>>>" + JSON.toJSONString(request.getParameterMap()));
                        System.out.println("=====消耗时间=====>>>>>" + executeTime + "ms");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }
        }).addPathPatterns("/**").excludePathPatterns("/","/user/login","/druid/*","/css/*","/fonts/*","/img/*","/js/*","/plugins/*","/favicon.ico");
    }
}
