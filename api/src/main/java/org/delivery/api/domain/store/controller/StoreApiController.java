package org.delivery.api.domain.store.controller;

import lombok.RequiredArgsConstructor;
import org.delivery.api.common.api.Api;
import org.delivery.api.domain.store.business.StoreBusiness;
import org.delivery.api.domain.store.controller.model.StoreResponse;
import org.delivery.db.store.enums.StoreCategory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/store")
public class StoreApiController {

    //가맹점 조회는 로그인 사용자만 가능

    private  final StoreBusiness storeBusiness;

    public  Api<List<StoreResponse>> search(@RequestParam(required = false)StoreCategory storeCategory){
        var response = storeBusiness.searchCategory(storeCategory);
        return Api.OK(response);
    }

}
