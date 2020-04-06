package com.next.zhn.film.controller.cinema.vo.request;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.next.zhn.film.controller.common.BaseVO;
import com.next.zhn.film.controller.exception.ParamErrorException;
import com.next.zhn.film.dao.entity.FilmCinemaT;
import lombok.Data;

@Data
public class CinemaRequestVO extends BaseVO {
    private Integer brandId=99;
    private Integer hallType=99;
    private Integer districtId=99;
    private Integer pageSize=20;
    private Integer nowPage=1;

    @Override
    public void checkParam() throws ParamErrorException {

    }

    public QueryWrapper<FilmCinemaT> getQueryWrapper(){
        QueryWrapper<FilmCinemaT> queryWrapper = new QueryWrapper<>();
        if(this.brandId!=null&&this.brandId!=99){
            queryWrapper.eq("brand_id",this.brandId);
        }
        if(this.hallType!=null&&this.hallType!=99){
            queryWrapper.like("hall_ids","#"+this.hallType+"#");
        }
        if(this.districtId!=null&&this.districtId!=99){
            queryWrapper.eq("area_id",this.districtId);
        }
        return queryWrapper;
    }
}
