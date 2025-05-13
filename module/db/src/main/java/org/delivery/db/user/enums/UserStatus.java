package org.delivery.db.user.enums;

import lombok.AllArgsConstructor;

//unregistered 라고 코드로 작성할 때 오탈자 방지와 값을 데이터베이스에 저장했을때 db로만 값이 있더라도 한누에 알아볼수 있는 장점이 생기고 그리고 이 외에 값이 있다면 잘못된 데이터가 삽입되는 거기 때문에 rdb에 가장 큰 특성인 정규화된 것을 망가트리기 때문에
//반드시 지정된 데이터가 들어갈 수 잇도록 이렇게 enum으로 관리하도록 한다.
@AllArgsConstructor
public enum UserStatus {
    REGISTERED("등록"),
    UNREGISTERED("해지");
    private final String description;
}
