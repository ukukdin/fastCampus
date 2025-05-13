package org.delivery.storeadmin.domain.user.controller.model;

import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.delivery.db.storeuser.enums.StoreUserRole;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StoreUserRegisterRequest {

    @NotBlank
    private String storeName;
    @NotBlank
    private String email;
    @NotBlank
    private String password;
    @NotBlank
    private StoreUserRole role;
}
