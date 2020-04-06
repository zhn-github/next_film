package com.next.zhn.film.controller.cinema.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class CinemaVO implements Serializable {

    private String address;
    private String cinemaName;
    private String minimumPrice;
    private String uuid;


}
