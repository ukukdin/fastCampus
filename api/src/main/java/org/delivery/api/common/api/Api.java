package org.delivery.api.common.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.delivery.api.common.error.ErrorCodeIfs;

import javax.validation.Valid;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Api<T> {

    private Result result;

    @Valid
    private T body;


    public static <T> Api<T> OK(T data){
        var api = new Api();
        api.result = Result.OK();
        api.body = data;
        return api;
    }
    //api의 오브젝트이다 왜냐하면 에러가 났을 경우 바디에 세팅할게 없기 때문에 제네릭의 경고를 없애주기 위해서 오브젝트로 세탕을 했고 여기에 바디를 없앴다
    //result 를 밖에서 만들어서 넘겨줄수 있어서 api에 세팅하는것도 있다
    public static Api<Object> ERROR(Result result){
        var api = new Api<Object>();
        api.result = Result.OK();
        api.body = result;
        return api;
    }
    // result 의 에러에다가 그냥 바로 넘겨서 세팅하는거
    public static Api<Object> ERROR(ErrorCodeIfs errorCodeIfs){
        var api = new Api<Object>();
        api.body = errorCodeIfs;
        return api;
    }

    //에러코드 인터페이스와 throwable 를 받아서 2개를 넘기는 거 하나 또는 에러 코드 인터페이스와 그 다음 description을 담아깆고 에러 객체를 맏늘어서
    //세팅하는거
    public static Api<Object> ERROR(ErrorCodeIfs errorCodeIfs, Throwable tx){
        var api = new Api<Object>();
        api.body = Result.ERROR(errorCodeIfs, tx);
        return api;
    }

    public static Api<Object> ERROR(ErrorCodeIfs errorCodeIfs, String description){
        var api = new Api<Object>();
        api.body = Result.ERROR(errorCodeIfs,description);
        return api;
    }
}
