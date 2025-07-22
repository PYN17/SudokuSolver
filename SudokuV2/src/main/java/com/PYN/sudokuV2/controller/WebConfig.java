package com.PYN.sudokuV2.controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;


@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        System.out.println("✅ WebConfig active — static file mapping set");

        registry.addResourceHandler("/**")
                .addResourceLocations("file:/static/");
    }
}

