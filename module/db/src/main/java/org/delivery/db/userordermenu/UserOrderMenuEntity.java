package org.delivery.db.userordermenu;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.delivery.db.BaseEntity;
import org.delivery.db.userordermenu.enums.UserOrderMenuStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@Table(name = "user_order_menu")
@Entity
public class UserOrderMenuEntity extends BaseEntity {
//중간에 연결시켜주는 곳이라서 크게 많은것을 가지고 있지 않는다. 추후 개발 검증할때 쓰거나 할때 h2에서 드랍 같은것들 때문에 컬럼을 사용한다. 특정 상황 예측 과 재현이 가능한다.
  //귀찮아도 작업을 해야된다 --> column
  @Column(nullable = false)
  private Long userOrderId;//1:N 관계
  @Column(nullable = false)
  private Long storeMenuId; //1:N 관계(스토어랑)

  @Enumerated(EnumType.STRING)
  @Column(length = 50, nullable = false)
  private UserOrderMenuStatus status; //상태

}
