package com.eltonkola.cingerdhing.config

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import org.springframework.web.servlet.resource.GzipResourceResolver
import org.springframework.web.servlet.resource.PathResourceResolver

@Configuration
class WebConfig : WebMvcConfigurer {

    override fun addResourceHandlers(registry: ResourceHandlerRegistry) {

        registry.addResourceHandler("/app-assets/**").addResourceLocations("classpath:/static/app-assets/")
        registry.addResourceHandler("/assets/**").addResourceLocations("classpath:/static/assets/")
        registry.addResourceHandler("/templates/**").addResourceLocations("classpath:/templates/")


//        registry!!
//                .addResourceHandler("/static/**")
//                .addResourceLocations("/static/")
//                .setCachePeriod(3600)
//                .resourceChain(true)
//                .addResolver(GzipResourceResolver())
//                .addResolver(PathResourceResolver())
    }
}
