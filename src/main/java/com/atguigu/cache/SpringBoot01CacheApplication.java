package com.atguigu.cache;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * 一、搭建基本环境
 * 1、导入数据库文件,创建出department和employee表
 * 2、创建javabean封装数据
 * 3、整合mybatis操作数据库
 *      1、配置数据源信息
 *      2、使用注解版的MyBatis;
 *          1)@MapperScan指定需要扫描的的mapper接口所在的包
 *二、快速体验缓存
 *      步骤:
 *      1、开启基于注解的缓存 @EnableCaching
 *      2、标注缓存注解即可
 *          @Cacheable 某一个方法是可缓存的
 *          @CacheEvict 清除缓存
 *          @Cacheput
 *
 *
 */
@MapperScan(basePackages = "com.atguigu.cache.mapper")
@SpringBootApplication
@EnableCaching
public class SpringBoot01CacheApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBoot01CacheApplication.class, args);
    }

}
