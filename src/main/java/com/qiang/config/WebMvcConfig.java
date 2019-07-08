package com.qiang.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @Author: qiang
 * @ProjectName: adminsystem
 * @Package: com.qiang.config
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
                        .addResourceLocations("classpath:/META-INF/resources/")
                        .addResourceLocations("classpath:/static/");
            }

            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("/").setViewName("index");
                registry.addViewController("/login.html").setViewName("login");
                registry.addViewController("/register.html").setViewName("register");
                registry.addViewController("/editor.html").setViewName("editor");
                registry.addViewController("/update.html").setViewName("update");
                registry.addViewController("/archive.html").setViewName("archive");
            }
        };
        return webMvcConfigurerAdapter;
    }
}
