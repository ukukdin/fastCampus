package org.delivery.api.domain.storemenu.converter;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.delivery.api.common.annotation.Converter;
import org.delivery.api.common.error.ErrorCode;
import org.delivery.api.common.exception.ApiException;
import org.delivery.api.domain.storemenu.controller.model.StoreMenuRegisterRequest;
import org.delivery.api.domain.storemenu.controller.model.StoreMenuResponse;
import org.delivery.db.storemenu.StoreMenuEntity;

@Converter
public class StoreMenuConverter {

  public StoreMenuEntity toEntity(StoreMenuRegisterRequest request){
    return Optional.ofNullable(request)
        .map(it ->{
          return StoreMenuEntity.builder()
              .storeId(request.getStoreId())
              .name(request.getName())
              .amount(request.getAmount())
              .thumbnail_url(request.getThumbnailUrl())
              .build();
        })
        .orElseThrow(()-> new ApiException(ErrorCode.NULL_POINT));
  }

  public StoreMenuResponse toResponse(
      StoreMenuEntity storeMenuEntity
    ){
    return Optional.ofNullable(storeMenuEntity)
        .map(it ->{
            return StoreMenuResponse.builder()
                .id(storeMenuEntity.getId())
                .name(storeMenuEntity.getName())
                .storeId(storeMenuEntity.getStoreId())
                .amount(storeMenuEntity.getAmount())
                .status(storeMenuEntity.getStatus())
                .thumbnailUrl(storeMenuEntity.getThumbnail_url())
                .likeCount(storeMenuEntity.getLike_count())
                .sequence(storeMenuEntity.getSequence())
                .build()
                ;

        })
        .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT));
  }

  public List<StoreMenuResponse> toResponse(
      List<StoreMenuEntity> list
  ){
    return list.stream().map(it -> toResponse(it))
        .collect(Collectors.toList());
  }

}
