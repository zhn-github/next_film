package com.next.zhn.film.controller.cinema.vo.response;

import lombok.Data;

import java.io.Serializable;

@Data
public class AreaResponseVO implements Serializable {

    private String areaId;
    private String areaName;
    private Boolean isActive = false;

}
