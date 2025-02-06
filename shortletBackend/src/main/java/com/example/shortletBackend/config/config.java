package com.example.shortletBackend.config;


import com.example.shortletBackend.dto.TextResponse;
import org.modelmapper.ModelMapper;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.Arrays;

@Configuration
@EnableWebMvc
public class config  {
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

    @Bean
    public FilterRegistrationBean corsFilterRegistration(){
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowCredentials(true);
        configuration.setExposedHeaders(Arrays.asList("Content-Type","Access-Control-Allow-Origin","Access-Control-Allow-Headers","Authorization","X-Requested-With"));
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200","https://courageous-piroshki-f9154b.netlify.app"));
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*");
        source.registerCorsConfiguration("/**",configuration);

        FilterRegistrationBean filter = new FilterRegistrationBean(new CorsFilter(source));
        filter.setOrder(0);
        return filter;


    }


    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder){
        return new RestTemplateBuilder().build();
    }


    @Bean
    public TextResponse returnText(){
        return new TextResponse();
    }
}
