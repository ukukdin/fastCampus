package org.delivery.api.config.objectmapper;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ObjectMapperConfig {

    @Bean
    public ObjectMapper objectMapper(){
        var objectMapper = new ObjectMapper();
        //8버전 이후에 나온 클래스들을 처리해두기 위해서 8버전모듈을 사용한다. optional 등등
        objectMapper.registerModule(new Jdk8Module());

        objectMapper.registerModule(new JavaTimeModule()); // <<-- local date

        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false); // 모르든 json field에 대해서는 무시한다.

        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

        //날짜와 관련된 직렬화
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        //스네이크 케이스
        objectMapper.setPropertyNamingStrategy(new PropertyNamingStrategies.SnakeCaseStrategy());


        return objectMapper;
    }
}
