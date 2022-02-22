package com.lxf.config;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.fill.Column;
import com.baomidou.mybatisplus.generator.fill.Property;

public class AutoGeneratorHelper {

    public static void main(String[] args) {
        FastAutoGenerator.create("jdbc:mysql://101.43.13.93:3306/spring_boot?useUnicode=yes&characterEncoding=utf8&useSSL=false&autoReconnect=true&serverTimezone=UTC"
                ,"root","Lxf_332521")
        .globalConfig(builder -> {
            builder.enableSwagger()
                    .author("lxf")                  //作者
                    .outputDir(System.getProperty("user.dir")+"/src/main/java") //输出路径
                    .disableOpenDir()
                    .dateType(DateType.ONLY_DATE);   //日期设置
        })
        .packageConfig(builder -> {
            builder.moduleName("userInfo")          //模块名
                    .parent("com.lxf.common")       //父路径
                    .entity("entity")               //  模块名下面的entity文件夹名字
                    .mapper("mapper")               //  模块名下面的mapper文件夹名字
                    .service("service")             //  模块名下面的service文件夹名字
                    .controller("controller");      //模块名下面的controller文件夹名字
        })
        .strategyConfig(builder -> {
            builder.addInclude("user_info")
                    //属性自动填充 基于数据库字段
                    .entityBuilder().addTableFills(new Column("create_time",FieldFill.INSERT))
                    ///属性自动填充 基于实体类属性填充
                    .addTableFills(new Property("updateTime",FieldFill.INSERT_UPDATE))
                    .versionPropertyName("version")
                    //开启驼峰转换
                    .columnNaming(NamingStrategy.underline_to_camel)
                    .logicDeletePropertyName("deleted");
        })
        .execute();

    }

    /**
     * 全局配置
     * @return
     */
    private static GlobalConfig globalConfig () {
        return new GlobalConfig.Builder()
                .author("lxf") //作者
                .outputDir(System.getProperty("user.dir")+"/src/main/java") //输出路径
                .disableOpenDir()
                .dateType(DateType.ONLY_DATE)   //日期设置
                .build();
    }

    /**
     * 数据源配置
     * @return
     */
    private static DataSourceConfig dataSourceConfig(){
        return new DataSourceConfig.Builder(
                "jdbc:mysql://101.43.13.93:3306/spring_boot?useUnicode=yes&characterEncoding=utf8&useSSL=false&autoReconnect=true&serverTimezone=UTC"
                ,"root","Lxf_332521")
                .build();
    }

    /**
     * 包配置
     * @return
     */
    private static PackageConfig packageConfig() {
        return new PackageConfig.Builder()
                .moduleName("userInfo") //模块名
                .parent("com.lxf.common")   //父路径
                .entity("entity")   //
                .mapper("mapper")   //
                .service("service") //
                .controller("controller")   //
                .build();
    }

    private static StrategyConfig strategyConfig() {
        return new StrategyConfig.Builder()
                .addInclude("user_info")
                //属性自动填充 基于数据库字段
                .entityBuilder().addTableFills(new Column("create_time",FieldFill.INSERT))
                ///属性自动填充 基于实体类属性填充
                .addTableFills(new Property("updateTime",FieldFill.INSERT_UPDATE))
                .versionPropertyName("version")
                //开启驼峰转换
                .columnNaming(NamingStrategy.underline_to_camel)
                .logicDeletePropertyName("deleted")
                .build();
    }
}
