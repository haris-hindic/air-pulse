package com.pulse.air.commons.configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan(basePackages = { "com.pulse.air.**.dao.model" })
public class BaseJpaConfiguration {

}
