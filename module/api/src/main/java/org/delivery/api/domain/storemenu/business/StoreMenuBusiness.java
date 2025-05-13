package org.delivery.api.domain.storemenu.business;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.delivery.api.common.annotation.Business;
import org.delivery.api.domain.storemenu.controller.model.StoreMenuRegisterRequest;
import org.delivery.api.domain.storemenu.controller.model.StoreMenuResponse;
import org.delivery.api.domain.storemenu.converter.StoreMenuConverter;
import org.delivery.api.domain.storemenu.service.StoreMenuService;

@RequiredArgsConstructor
@Business
public class StoreMenuBusiness {

  private final StoreMenuService storeMenuService;

  private final StoreMenuConverter storeMenuConverter;

  public StoreMenuResponse register(
      StoreMenuRegisterRequest request){
    //req -> entity -> save -> response
    var entity = storeMenuConverter.toEntity(request);
    var newEntity = storeMenuService.register(entity);
    var response = storeMenuConverter.toResponse(newEntity);
    return response;
  }

  public List<StoreMenuResponse> search(
      Long storeId
  ){
      var list = storeMenuService.getStoreMenuByStoreId(storeId);

      return list.stream()
          .map(it ->{
            return storeMenuConverter.toResponse(it);
          })
          //.map(storeMenuConverter::toResponse) 위와 동일한 코드입니다.
          .collect(Collectors.toList());
  }
}
