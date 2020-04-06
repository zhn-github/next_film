package com.next.zhn.film.controller.cinema.vo.response;

import lombok.Data;

import java.io.Serializable;

@Data
public class CinemaResponseVO implements Serializable {
    private Integer cinemaId;
    private String cinemaName;
    private String cinemaPhone;
    private String cinemaAddress;
    private String imgUrl;

}
