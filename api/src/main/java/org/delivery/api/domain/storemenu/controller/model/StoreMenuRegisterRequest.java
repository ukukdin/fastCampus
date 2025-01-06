package org.delivery.api.domain.storemenu.controller.model;

import java.math.BigDecimal;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StoreMenuRegisterRequest {

  @NotNull
  private Long storeId;
  @NotBlank
  private String name;
  @NotNull
  private BigDecimal amount;
  @NotBlank
  private String thumbnailUrl;


}
