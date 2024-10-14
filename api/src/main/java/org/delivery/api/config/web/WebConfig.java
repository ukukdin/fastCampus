package org.delivery.api.config.web;

import lombok.RequiredArgsConstructor;
import org.delivery.api.interceptor.AuthorizationInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

//webmvc를 담당하는 핸들러를 상속받는다.
@RequiredArgsConstructor
@Configuration
public class  WebConfig implements WebMvcConfigurer {

    private final AuthorizationInterceptor authorizationInterceptor;

    //openAPI로 시작하는것들은 검증을 안할것이고 나머지 것을은 검증을 한다는것에 대한 룰을 만들어서 나눌것이다.
    private List<String> OPEN_API = List.of(
            "/open-api/**"
    );

    //프로젝트에 root 뚫어줄것이다 그 다음에 favicon이라고 아이콘 받아가는것 뚫어주고 에러도 검증하지 않는다.
    private List<String> DEFAULT_EXCLUDE = List.of(
            "/",
            "favicon.ico",
            "/error"
    );

    private List<String> SWAGGER = List.of(
            "/swagger-ui.html",
            "/swagger-ui/**",
            "/v3/api-docs/**"
    );
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authorizationInterceptor)
            .excludePathPatterns(OPEN_API)
            .excludePathPatterns(DEFAULT_EXCLUDE)
            .excludePathPatterns(SWAGGER)
        ;

    }
}
