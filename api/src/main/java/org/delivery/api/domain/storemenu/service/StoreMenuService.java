package org.delivery.api.domain.storemenu.service;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.delivery.api.common.error.ErrorCode;
import org.delivery.api.common.exception.ApiException;
import org.delivery.db.storemenu.StoreMenuEntity;
import org.delivery.db.storemenu.StoreMenuRepository;
import org.delivery.db.storemenu.enums.StoreMenuStatus;
import org.springframework.stereotype.Service;
@RequiredArgsConstructor
@Service
public class StoreMenuService {

  private final StoreMenuRepository storeMenuRepository;

  public StoreMenuEntity register(
      StoreMenuEntity storeMenuEntity
  ){
    return Optional.ofNullable(storeMenuEntity)
        .map(it ->{
          storeMenuEntity.setStatus(StoreMenuStatus.REGISTERED);
          return storeMenuRepository.save(it);
        })
        .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT));
  }

}
