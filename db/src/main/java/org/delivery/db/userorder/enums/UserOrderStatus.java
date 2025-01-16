package org.delivery.db.userorder.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum UserOrderStatus {

  REGISTERED("등록"),
  UNREGISTERED("해지"),
  ;
/* lombok을 사용하지 않으면 이걸 만들어야한다.
 UserOrderStatus(String description){
    this.description = description;
  }*/
  private  String description;


  }
