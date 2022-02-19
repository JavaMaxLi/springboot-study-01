package com.lxf.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

import static springfox.documentation.service.ApiInfo.DEFAULT_CONTACT;

/**
 * @author LiXiaoFeng
 * @date 2022年02月19日 13:26
 */
@Configuration
@EnableSwagger2 //开启swagger2
public class SwaggerConfig {

    @Value("${spring.profiles.active}")
    private String environment;

    @Bean
    public Docket docket() {
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                //RequestHandlerSelectors   配置要扫描的controller路径
                .apis(RequestHandlerSelectors.basePackage("com.lxf.controller"))
                //扫描标有指定注解的类
                //.apis(RequestHandlerSelectors.withClassAnnotation(Controller.class))
                //扫描指定注解的方法
                //.apis(RequestHandlerSelectors.withMethodAnnotation(RequestMapping.class))
                .build()
                .groupName("lxf");
        //只在测试环境开启，生产环境不开启
        if("dev".equals(environment)) {
            docket.enable(true);
        } else {
            docket.enable(false);
        }
        return docket;
    }

    /**
     * 多个分组
     * @return
     */
    @Bean
    public Docket docket1() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("gq");
    }

    private ApiInfo apiInfo(){
        Contact contact = new Contact("李小锋", "localhost:8080/swagger-ui.html", "913577706@qq.com");
        return new ApiInfo("开源的后台管理系统", "springboot-swagger-mybatisplus-rabbitmq", "1.0", "urn:tos", contact, "Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0", new ArrayList());
    }
}
