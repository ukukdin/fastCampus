package org.delivery.api.domain.userorder.controller.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.delivery.api.domain.store.controller.model.StoreResponse;
import org.delivery.api.domain.storemenu.controller.model.StoreMenuResponse;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserOrderDetailResponse {
    //사용자 주문건
    private UserOrderResponse userOrderResponse;
    private StoreResponse storeResponse;
    //메뉴 들어있는지
    private List<StoreMenuResponse> storeMenuResponseList;

}
