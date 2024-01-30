package dev.line4.blackBoard.utils.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity(debug = true)
public class CorsWithSecurityConfig implements WebMvcConfigurer {

    @Override // CORS 설정 정의 - 특정 출처 ("http://localhost:3000") 에서 오는 특정 HTTP 메소드 허용
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
                .allowedOrigins("http://localhost:3000")
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowCredentials(true);
    }

    @Bean // 어떤 보약 제약 없이 모든 요청 수락
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable(); // CSRF 보호 비활성화
        http.authorizeRequests().anyRequest().permitAll(); // 모든 요청 수락
        return http.build();
    }

}
