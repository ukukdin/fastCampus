package org.delivery.api.domain.userordermenu.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.delivery.db.userordermenu.UserOrderMenuEntity;
import org.delivery.db.userordermenu.UserOrderMenuRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserOrderMenuService {

  private final UserOrderMenuRepository userOrderMenuRepository;

  public List<UserOrderMenuEntity> getUserOrderMenu(Long UserOrderId){

  }
}
