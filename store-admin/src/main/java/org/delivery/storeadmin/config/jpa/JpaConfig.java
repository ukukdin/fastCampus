package org.delivery.storeadmin.config.jpa;

import org.springframework.context.annotation.Configuration;

@Configuration
@EntityScan(basePackages = "org.delivery.db")
public class JpaConfig {

}
