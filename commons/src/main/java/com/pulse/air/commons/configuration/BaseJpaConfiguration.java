package com.pulse.air.commons.configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EntityScan(basePackages = { "com.pulse.air.**.dao.model" })
public class BaseJpaConfiguration {

}
