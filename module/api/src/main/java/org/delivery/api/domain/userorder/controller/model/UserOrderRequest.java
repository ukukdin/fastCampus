package org.delivery.api.domain.userorder.controller.model;

import java.util.List;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserOrderRequest {

  // 주문
  // 특정 사용자가, 특정 메뉴를 주문
  // 특정 사용자 = 로그인된 세션에 들어있는 사용자
  // 특정 메뉴 ID

  @NotNull
  private List<Long> storeMenuIdList;

}
