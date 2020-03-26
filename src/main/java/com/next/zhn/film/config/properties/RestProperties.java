package com.next.zhn.film.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @author 张浩男
 */
@Data
@Configuration
@Component
@ConfigurationProperties(prefix = RestProperties.REST_PREFIX)
public class RestProperties {

    public static final String REST_PREFIX = "rest";
    private Boolean authOpen;
}
