package com.lxf.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * MybatisPlus配置类
 */
@Configuration
public class MybatisPlusConfig {

    /**
     * 自定义插件
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        //MybatisPlus乐观锁插件
        //     *  1.支持的数据类型只有:int,Integer,long,Long,Date,Timestamp,LocalDateTime
        //     *  2.整数类型下 newVersion = oldVersion + 1
        //     *  3.newVersion 会回写到 entity 中
        //     *  4.仅支持 updateById(id) 与 update(entity, wrapper) 方法
        //     *  5.在 update(entity, wrapper) 方法下, wrapper 不能复用!!!
        //     * @return
        interceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());

        //新的分页插件,一缓和二缓遵循mybatis的规则,需要设置 MybatisConfiguration#useDeprecatedExecutor = false
        // 避免缓存出现问题(该属性会在旧插件移除后一同移除)
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.H2));
        return interceptor;
    }
}
