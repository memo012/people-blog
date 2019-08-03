package com.qiang.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @Author: qiang
 * @ProjectName: adminsystem
 * @Package: com.qiang.common.config
 * @Description: 路径映射配置
 * @Date: 2019/6/27 0027 17:24
 **/
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
    }
    @Bean
    public WebMvcConfigurerAdapter webMvcConfigurerAdapter() {
        WebMvcConfigurerAdapter webMvcConfigurerAdapter = new WebMvcConfigurerAdapter() {

            @Override
            public void addResourceHandlers(ResourceHandlerRegistry registry) {
                registry.addResourceHandler("/**")
                        .addResourceLocations("classpath:/static/")
                        .addResourceLocations("file:/E:/vblog/");
                registry.addResourceHandler("/article/**")
                        .addResourceLocations("classpath:/static/");
                registry.addResourceHandler("/categories/**")
                        .addResourceLocations("classpath:/static/");
                registry.addResourceHandler("/templates/**")
                        .addResourceLocations("classpath:/static/");
                registry.addResourceHandler("/time/**")
                        .addResourceLocations("classpath:/static/");
                registry.addResourceHandler("/es/**")
                        .addResourceLocations("classpath:/static/");
            }

        };
        return webMvcConfigurerAdapter;
    }
}
