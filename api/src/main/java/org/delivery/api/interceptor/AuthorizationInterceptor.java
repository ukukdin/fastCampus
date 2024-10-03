package org.delivery.api.interceptor;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@AllArgsConstructor
@Component
public class AuthorizationInterceptor  implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("Authorization Interceptor url : {}", request.getRequestURI());

        //WEB, Chrome의 경우 GET, POST 전에 OPTIONs로 지원하는지 아닌지 확인을 하는것을 처리한다.
        if(HttpMethod.OPTIONS.matches(request.getMethod())){
            return true;
        }

        //resource를 검증( 즉 js라든지 html을 받아가는 요청이라면 핸들러가 instance of resourceHttp
        if(handler instanceof ResourceHttpRequestHandler){
            return true;
        }
        // TODO header 검증

        return true; //일단 통과 처리
    }
}
