package com.ajou.roommate_demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * @PACKAGE_NAME: com.ajou.roommate_demo.config
 * @CLASS_NAME: MvcConfig
 * @USER: BTS&ARMY
 * @Date 2024/11/12 16:27
 * @Version 1.0
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer {

    // 配置 JSP 视图解析器
    @Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/jsp/");  // JSP 文件的位置
        resolver.setSuffix(".jsp");  // JSP 文件的后缀
        return resolver;
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");  // 设置默认的 login 页面
    }
}