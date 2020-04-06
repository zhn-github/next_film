package com.next.zhn.film.controller.cinema.vo.response;

import lombok.Data;

import java.io.Serializable;

@Data
public class HalltypeResponseVO implements Serializable {
    private String halltypeName;
    private String halltypeId;
    private Boolean isActive = false;
}
