package com.qiang;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan(basePackages = "com.qiang.modules.sys.mapper")
@EnableCaching
@EnableAsync // 开启异步任务
@EnableScheduling // 开启定时任务
public class BlogApplication{

    public static void main(String[] args) {
        SpringApplication.run(BlogApplication.class, args);
    }

//    @Override//为了打包springboot项目
//    protected SpringApplicationBuilder configure(
//            SpringApplicationBuilder builder) {
//        return builder.sources(this.getClass());
//    }

}
