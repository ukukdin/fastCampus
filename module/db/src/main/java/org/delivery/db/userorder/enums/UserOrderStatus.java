package org.delivery.db.userorder.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum UserOrderStatus {

  REGISTERED("등록"),
  UNREGISTERED("해지"),

  //주문상태
  ORDER("주문"),
  //확인 상태
  ACCEPT("확인"),
  //요리중
  COOKING("요리중"),
  //배달중
  DELIVERY("배달중"),
  //배달 완료
  RECEIVE("배달완료")
  ;
/* lombok을 사용하지 않으면 이걸 만들어야한다.
 UserOrderStatus(String description){
    this.description = description;
  }*/
  private  String description;


  }
