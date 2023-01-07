package com.yzm.payment.config;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Collections;

public class MyGenerator {

    private static final String url = "jdbc:mysql://192.168.101.129:3306/mydb?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai";
    private static final String username = "root";
    private static final String password = "123456";

    // 模块为空字符串""或"/模块名" 子工程 subproject
    private static final String subproject = "/provider-payment";
    private static final String parent = "com.yzm";
    private static final String moduleName = "payment";
    //需要生成实体类等基础信息的表名，多个用逗号隔开
    private static final String[] tables = {"payment"};

    public static void main(String[] args) {
        FastAutoGenerator.create(url, username, password)
                // 全局配置
                .globalConfig(builder -> builder
                        .author("Yzm") // 设置作者
                        .commentDate("yyyy/MM/dd") // 注释日期
                        .disableOpenDir() // 禁用打开目录
                        .enableSwagger() // 开启 swagger 模式
                        .dateType(DateType.TIME_PACK)   //定义生成的实体类中日期的类型 TIME_PACK=LocalDateTime;ONLY_DATE=Date;
                        .outputDir(System.getProperty("user.dir") + subproject + "/src/main/java") // 指定输出目录
                        .build()
                )
                // 包配置
                .packageConfig(builder -> builder
                        .parent(parent) // 设置父包名
                        .moduleName(moduleName)
                        .entity("entity")
                        .mapper("mapper")
                        .service("service")
                        .serviceImpl("service.impl")
                        .controller("controller")
                        .xml("mapper.xml")
                        .pathInfo(Collections.singletonMap(OutputFile.xml, System.getProperty("user.dir") + subproject + "/src/main/resources/mapper/")) // 设置mapperXml生成路径
                        .build()
                )
                // 策略配置
                .strategyConfig(builder -> builder
                        .addInclude(tables) // 设置需要生成的表名
                        //.addTablePrefix("过滤表前缀")
                        //.addFieldPrefix("过滤字段前缀")
                        // 实体类配置
                        .entityBuilder()
                        .enableLombok() // 开启Lombok
                        .enableChainModel() // 链式模式
                        .enableTableFieldAnnotation() // 表列名称注释
                        // controller层配置
                        .controllerBuilder()
                        .enableRestStyle()  //开启生成 @RestController 控制器
                        // Mapper策略配置
                        .mapperBuilder()
                        .enableBaseResultMap()  //启用 BaseResultMap 生成
                        .enableBaseColumnList() //启用 BaseColumnList
                        .build()
                )
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
}
