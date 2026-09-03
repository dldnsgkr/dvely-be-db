package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * dvely 가 배포 시 주입하는 QEPLOY_ALLOWED_ORIGINS(프론트 오리진, 콤마 구분)를 읽어 CORS 허용 오리진으로
 * 쓴다. 비면 CORS 매핑을 안 걸어(같은 오리진만) — dvely 백엔드 템플릿의 CORS 규약 예시.
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Value("${QEPLOY_ALLOWED_ORIGINS:}")
    private String allowedOrigins;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        if (allowedOrigins == null || allowedOrigins.isBlank()) {
            return;
        }
        registry.addMapping("/**")
                .allowedOrigins(allowedOrigins.split(","))
                .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS")
                .allowedHeaders("*");
    }
}
