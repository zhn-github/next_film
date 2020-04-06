package com.next.zhn.film.controller.cinema.vo.response;

import lombok.Data;

import java.io.Serializable;

@Data
public class BrandResponseVO implements Serializable {

    private String brandName;
    private String brandId;
    private Boolean isActive = false;

}
