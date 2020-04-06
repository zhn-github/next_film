package com.next.zhn.film.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * jwt相关配置
 *
 * @author jiangzh
 * @date 2019-03-23 9:23
 */
@Data
@Component
@Configuration
@ConfigurationProperties(prefix = FilmProperties.FILM_PREFIX)
public class FilmProperties {

    public static final String FILM_PREFIX = "film";

    private String imgPre;



}
