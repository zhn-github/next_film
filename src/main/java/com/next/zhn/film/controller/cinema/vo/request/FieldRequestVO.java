package com.next.zhn.film.controller.cinema.vo.request;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.next.zhn.film.controller.common.BaseVO;
import com.next.zhn.film.controller.exception.ParamErrorException;
import com.next.zhn.film.dao.entity.FilmCinemaT;
import lombok.Data;

@Data
public class FieldRequestVO extends BaseVO {
    private String  cinemaId;
    private String fieldId;


    @Override
    public void checkParam() throws ParamErrorException {

    }

}
