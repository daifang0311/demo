package com.leyou.config;

import com.leyou.utils.CorsProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.List;

@Configuration
public class GlobalCorsConfig {//跨域请求拦截器

    @Bean
    public CorsFilter corsFilter(CorsProperties prop) {
        //1.添加CORS配置信息
        CorsConfiguration config = new CorsConfiguration();
        //1) 允许的域,不要写*，否则cookie就无法使用了
        config.addAllowedOrigin("http://manage.leyou.com");
        //2) 是否发送Cookie信息
        config.setAllowCredentials(true);
        //3) 允许的请求方式
        config.addAllowedMethod("OPTIONS");
        config.addAllowedMethod("HEAD");
        config.addAllowedMethod("GET");
        config.addAllowedMethod("PUT");
        config.addAllowedMethod("POST");
        config.addAllowedMethod("DELETE");
        config.addAllowedMethod("PATCH");
        // 4）允许的头信息
        config.addAllowedHeader("*");
        //2.添加映射路径，我们拦截一切请求
        UrlBasedCorsConfigurationSource configSource = new UrlBasedCorsConfigurationSource();
        configSource.registerCorsConfiguration("/**", config);
        //3.返回新的CorsFilter.
        return new CorsFilter(configSource);

//        //1.添加CORS配置信息
//        CorsConfiguration config = new CorsConfiguration();
//        //1) 允许的域,不要写*，否则cookie就无法使用了
//        for (String allowedOrigin : prop.getAllowedOrigins()) {
//            config.addAllowedOrigin(allowedOrigin);
//        }
//        //2) 是否发送Cookie信息
//        config.setAllowCredentials(prop.getAllowCredentials());
//        //3) 允许的请求方式
//        for (String allowedMethod : prop.getAllowedMethods()) {
//            config.addAllowedMethod(allowedMethod);
//        }
//        // 4）允许的头信息
//        for (String allowedHeader : prop.getAllowedHeaders()) {
//            config.addAllowedHeader(allowedHeader);
//        }
//        // 5）设置有效期
//        config.setMaxAge(prop.getMaxAge());
//
//        //2.添加映射路径，我们拦截一切请求
//        UrlBasedCorsConfigurationSource configSource = new UrlBasedCorsConfigurationSource();
//        configSource.registerCorsConfiguration(prop.getFilterPath(), config);
//
//        //3.返回新的CorsFilter.
//        return new CorsFilter(configSource);
    }
}
