package com.next.zhn.film.dao.mapper;

import com.next.zhn.film.controller.cinema.vo.CinemaFilmInfoVO;
import com.next.zhn.film.controller.cinema.vo.CinemaFilmVO;
import com.next.zhn.film.controller.cinema.vo.FieldHallInfoVO;
import com.next.zhn.film.dao.entity.FilmFieldT;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * <p>
 * 放映场次表 Mapper 接口
 * </p>
 *
 * @author zhn
 * @since 2020-03-27
 */
@Repository
public interface FilmFieldTMapper extends BaseMapper<FilmFieldT> {

    /**
     * 查询影院信息
     * @param cinemaId 影院ID
     * @return
     */
    List<CinemaFilmVO> describeFieldListByCinemaId(@Param("cinemaId") String cinemaId);

    /**
     * 查询影片信息
     * @param fildId 影片ID
     * @return
     */
    CinemaFilmInfoVO describeFieldInfoByFildId(@Param("fildId") String fildId);

    /**
     * 查询放映信息
     * @param fildId
     * @return
     */
    FieldHallInfoVO describeHallInfoByFildId(@Param("fildId") String fildId);
}
