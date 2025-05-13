package org.delivery.api.exceptionhandler;

import lombok.extern.slf4j.Slf4j;
import org.delivery.api.common.api.Api;
import org.delivery.api.common.exception.ApiException;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
@Order(value = Integer.MAX_VALUE) //최우선처리
public class ApiExceptionHandler {

    //exception 핸드러라는 어노테이션을 통해서 모든 Apiexception.class로 발생하는 모든 예외를 다 캐치한다.
    @ExceptionHandler(value = ApiException.class)
    public ResponseEntity<Api<Object>> apiException(ApiException apiException){
        log.error("", apiException); //위와 같은 apiException은 spring boot가 넣어줄것이고 동일하게 여기다가 log error stacktrace 찍을겁니다. 왜 stacktrace가 가능하냐?
        //apiException은 runtimeexception을 상속받았기 때문이다.

        var errorCode = apiException.getErrorCodeIfs();

        return ResponseEntity
                .status(errorCode.getHttpStatusCode())
                .body(
                        Api.ERROR(errorCode, apiException.getErrorDescription())
                );
    }
}
