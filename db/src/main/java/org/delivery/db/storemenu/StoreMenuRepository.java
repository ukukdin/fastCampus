package org.delivery.db.storemenu;

import org.delivery.db.storemenu.enums.StoreMenuStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface StoreMenuRepository extends JpaRepository<StoreMenuEntity, Long> {

    //유효한 메뉴체크
    //select * from store_menu whre id =? and status = ? order by id desc limit 1
    Optional<StoreMenuEntity> findFirstByIdAndStatusOrderByIdDesc(Long id, StoreMenuStatus status);

    //특정 가게의 메뉴 가져오기g
    // select * from store_menu where id =? and status = ? rorder by seqeunce desc;
    List<StoreMenuEntity> findAllByStoreIdAndStatusOrderBySequenceDesc(Long storeId, StoreMenuStatus status);

}
