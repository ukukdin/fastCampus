package org.delivery.api.common.exception;

import org.delivery.api.common.error.ErrorCodeIfs;

/*
* 인터페이스 메소드 반드시 정의해야되고 그 다음에 string getErrorCode desciption이라고 이 두가지 메소드는 우리가 커스텀한 exception에서는 반드시 정의해야 된다 라고 정의를 한다.
*/
public interface ApiExceptionIfs {

    ErrorCodeIfs getErrorCodeIfs();

    String getErrorDescription();
}
