package org.delivery.api.interceptor;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.delivery.api.common.error.ErrorCode;
import org.delivery.api.common.error.TokenErrorCode;
import org.delivery.api.common.exception.ApiException;
import org.delivery.api.domain.token.business.TokenBusiness;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

@Slf4j
@AllArgsConstructor
@Component
public class AuthorizationInterceptor  implements HandlerInterceptor {
    private final TokenBusiness tokenBusiness;
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
        var accessToken = request.getHeader("authorization-token");
        if(accessToken == null){
            throw  new ApiException(TokenErrorCode.AUTHORIZATION_TOKEN_NOT_FOUND);
        }
        var userId = tokenBusiness.validationAccessToken(accessToken);
        if(userId != null) {
            //null 일때는 objects.requirenonnull에서 예외가 발생할것이다.
            var requestContext = Objects.requireNonNull(RequestContextHolder.getRequestAttributes());
            requestContext.setAttribute("userId", userId, RequestAttributes.SCOPE_REQUEST);
            return true;
        }
        throw new ApiException(ErrorCode.BAD_REQUEST,"인증 실패");

    }
}
