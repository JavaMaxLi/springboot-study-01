package com.lxf.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashMap;

/**
 * Druid监控配置
 * @author LiXiaoFeng
 * @date 2022年01月19日 15:53
 */
@Configuration
public class DruidConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource (){
        return new DruidDataSource();
    }

    /**
     * springboot内置servlet容器，ServletRegistrationBean代替了web.xml
     * @return
     */
    @Bean
    public ServletRegistrationBean startServlet() {
        //添加一个Servlet
        ServletRegistrationBean<StatViewServlet> bean = new ServletRegistrationBean<>(new StatViewServlet(),"/druid/*");
        HashMap<String, String> hashMap = new HashMap<>();
        //登录账号密码
        hashMap.put("loginUsername","admin");
        hashMap.put("loginPassword","123456");

        //允许谁访问
        hashMap.put("allow","localhost");

        bean.setInitParameters(hashMap);

        return bean;
    }
}
