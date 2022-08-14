package net.markz.checkout.api.configs;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@Slf4j
public class CorsConfigurer implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {

        log.debug("Adding cors mapping");
        registry.addMapping("/**")
                .allowedOrigins(
                        "http://localhost:80"
                )
                .allowedMethods("GET");

        log.debug("Added cors mapping");

    }
}