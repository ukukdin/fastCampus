package org.delivery.api.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Slf4j
@Component
public class LoggerFilter implements Filter {


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        var req = new ContentCachingRequestWrapper((HttpServletRequest) request);
        var res = new ContentCachingResponseWrapper((HttpServletResponse) response);
        log.info("INIT URI : {}", req.getRequestURI());
        chain.doFilter(req, res);

        //reqeust info

        var headerNames = req.getHeaderNames();
        var headerValues = new StringBuilder();

        headerNames.asIterator().forEachRemaining(headerKey ->{
            var headerValue = req.getHeader(headerKey);

            // authorization-token : ??? , user-agent : ???
            headerValues
                    .append(" [")
                    .append(headerKey)
                    .append(" : ")
                    .append(headerValue)
                    .append("] ");
        });

        var requestBody = new String(req.getContentAsByteArray()); //content를  byte로 담는다.
        var uri = req.getRequestURI();
        var method = req.getMethod();
        log.info(">>>>> uri : {}, method : {},  header : {}, body : {}", headerValues, requestBody);

        //response info

        var responseHeaderValues = new StringBuilder();

        res.getHeaderNames().forEach(headerKey ->{
            var headerValue = res.getHeader(headerKey);
            responseHeaderValues
                    .append("[")
                    .append(headerKey)
                    .append(" : ")
                    .append(headerValue)
                    .append("] ");

        });

        var responseBody = new String(res.getContentAsByteArray());

        log.info("<<<<<<uri : {}, method : {}, header : {},  body: {} ", responseHeaderValues, responseBody);


        res.copyBodyToResponse(); // 이거는 반드시 넣어야 한다 그렇지 않으면 리스폰스 바디가 비어져서 가기 때문에 반드시 넣어줘야한다.
        //그리고 response, reqeust info 는 append로 쓰여진다.


    }
}
