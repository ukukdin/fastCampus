package org.delivery.api.common.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.delivery.api.common.error.ErrorCode;
import org.delivery.api.common.error.ErrorCodeIfs;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Result {

    private Integer resultCode;

    private String resultMessage;

    private String resultDescription;

    public static Result OK(){
     return Result.builder()
             .resultCode(ErrorCode.OK.getErrorCode())
             .resultMessage(ErrorCode.OK.getDescription())
             .resultDescription("성공")
             .build();
    }

    public static Result ERROR(ErrorCodeIfs errorCodeIfs){
        return Result.builder()
                .resultCode(errorCodeIfs.getErrorCode())
                .resultMessage(errorCodeIfs.getDescription())
                .resultDescription("에러 발생")
                .build();
    }
    public static Result ERROR(ErrorCodeIfs errorCodeIfs,Throwable tx){
        return Result.builder()
                .resultCode(errorCodeIfs.getErrorCode())
                .resultMessage(errorCodeIfs.getDescription())
                .resultDescription(tx.getLocalizedMessage()) //조금 위험할수 잇는 코드이지만 추후에 배움으로 이해할수 잇을것이다. 그 이후는 서버의 모든 스텍트렉스가 다 내려간다.
                .build();
    }
    //평균적으로 많이 사용하는 경우 ( 에러코드와 어떤 상황이다라는 description 넘겨주면 에러코드와 메세지 세팅하고 description 담아주는것)
    //현재 원하는 값이 담기지 않아서 오류 수정을 해야됩니다.

    public static Result ERROR(ErrorCodeIfs errorCodeIfs, String description){
        return Result.builder()
                .resultCode(errorCodeIfs.getErrorCode())
                .resultMessage(errorCodeIfs.getDescription())
                .resultDescription(description)
                .build();
    }
}
