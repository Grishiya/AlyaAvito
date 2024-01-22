package ru.skypro.homework.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

import java.io.File;

@Configuration
@EnableWebMvc
public class SecurityConfig implements WebMvcConfigurer {
    @Value("${image-directory}")
    private String path;
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/content/**").allowedOrigins("http://localhost:3000");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String location = "file:" + new File(path).getAbsolutePath().replace("\\", "/") + "/";
        ;
        registry.addResourceHandler("/content/**")
                .addResourceLocations(location + "Ad/", location + "UserEntity/")
                .resourceChain(true)
                .addResolver(new PathResourceResolver());
    }
}
