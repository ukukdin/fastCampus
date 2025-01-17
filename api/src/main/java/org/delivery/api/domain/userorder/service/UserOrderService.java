package org.delivery.api.domain.userorder.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import net.bytebuddy.asm.Advice.Local;
import org.delivery.api.common.error.ErrorCode;
import org.delivery.api.common.exception.ApiException;
import org.delivery.db.userorder.UserOrderEntity;
import org.delivery.db.userorder.UserOrderRepository;
import org.delivery.db.userorder.enums.UserOrderStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserOrderService {

  private final UserOrderRepository userOrderRepository;

  //주문 등등 여러가지들이 있으니 인터페이스를 만들어보자;
  //해당 유저의 주문을 가져오는 구문

  public UserOrderEntity getUserOrderWithThrow(
    Long id, Long userId
  ){
    return userOrderRepository.findAllByIdAndStatusAndUserId(id, UserOrderStatus.REGISTERED, userId)
        .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT));
  }

  //모든 주문 내역 불러오는 구문
  public List<UserOrderEntity> getUserOrderList(Long userId){
    return userOrderRepository.findAllByUserIdAndStatusOrderByIdDesc(userId, UserOrderStatus.REGISTERED);
  }
  public List<UserOrderEntity> getUserOrderList(Long userId, List<UserOrderStatus> statusList){
    return userOrderRepository.findAllByUserIdAndStatusInOrderByIdDesc(userId, statusList);
  }

  //주문(create)
  public UserOrderEntity order(
      UserOrderEntity userOrderEntity
  ){
    return Optional.ofNullable(userOrderEntity)
        .map(it ->{
          it.setStatus(UserOrderStatus.ORDER);
          it.setOrderedAt(LocalDateTime.now());
          return userOrderRepository.save(it);

        })
        .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT));
  }

  /*각각 만들기 보다는  공통 메서드 하나만 만들자 상태변경 관련*/
  public UserOrderEntity setStatus(UserOrderEntity userOrderEntity, UserOrderStatus status){
    userOrderEntity.setStatus(status);
    return userOrderRepository.save(userOrderEntity);
  }
  // 나의 주문 내역 과 과거 주문 내역을 볼 수 있는것을 처리해보자
  //현재 진행중인 내역
  public List<UserOrderEntity> current(Long userId){
    return getUserOrderList(
        userId,
        List.of(
            UserOrderStatus.ORDER,
            UserOrderStatus.COOKING,
            UserOrderStatus.DELIVERY,
            UserOrderStatus.ACCEPT
        )
    );
  }
  //과거 주문한 내역
  public List<UserOrderEntity> history(Long userId){
    return getUserOrderList(
        userId,
        List.of(
            UserOrderStatus.RECEIVE
        )
    );
  }

  //주문 확인(update)
  public UserOrderEntity accept(UserOrderEntity userOrderEntity){
    userOrderEntity.setAcceptedAt(LocalDateTime.now());
    return setStatus(userOrderEntity, UserOrderStatus.ACCEPT);
  }
  //조리 시작
  public UserOrderEntity cooking(UserOrderEntity userOrderEntity){
    userOrderEntity.setCookingStartedAt(LocalDateTime.now());
    return setStatus(userOrderEntity, UserOrderStatus.COOKING);
  }
  //베달 시작
  public UserOrderEntity delivery(UserOrderEntity userOrderEntity){
    userOrderEntity.setDeliveryStartedAt(LocalDateTime.now());
    return setStatus(userOrderEntity, UserOrderStatus.DELIVERY);
  }
  //배달 완료
  public UserOrderEntity receive(UserOrderEntity userOrderEntity){
    userOrderEntity.setReceivedAt(LocalDateTime.now());
    return setStatus(userOrderEntity, UserOrderStatus.RECEIVE);
  }

}
