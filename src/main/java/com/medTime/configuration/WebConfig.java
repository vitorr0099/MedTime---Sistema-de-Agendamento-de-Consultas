package com.medTime.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/css/**")
                .addResourceLocations("classpath:/templates/Trabalho/css/");
        
        registry.addResourceHandler("/js/**")
                .addResourceLocations("classpath:/templates/Trabalho/js/");
        
        registry.addResourceHandler("/logo/**")
                .addResourceLocations("classpath:/templates/Trabalho/logo/");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/ginecologista").setViewName("Trabalho/medicos");
        registry.addViewController("/ortopedia").setViewName("Trabalho/medicos");
        registry.addViewController("/medico-detalhes").setViewName("Trabalho/medico-detalhes");
        registry.addViewController("/meus-agendamentos").setViewName("Trabalho/meus-agendamentos");
    }
} 