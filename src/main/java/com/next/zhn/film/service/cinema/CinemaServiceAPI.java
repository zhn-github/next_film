package com.next.zhn.film.service.cinema;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.next.zhn.film.controller.cinema.vo.CinemaFilmInfoVO;
import com.next.zhn.film.controller.cinema.vo.CinemaFilmVO;
import com.next.zhn.film.controller.cinema.vo.FieldHallInfoVO;
import com.next.zhn.film.controller.cinema.vo.request.CinemaRequestVO;
import com.next.zhn.film.controller.cinema.vo.CinemaVO;
import com.next.zhn.film.controller.cinema.vo.response.AreaResponseVO;
import com.next.zhn.film.controller.cinema.vo.response.BrandResponseVO;
import com.next.zhn.film.controller.cinema.vo.response.CinemaResponseVO;
import com.next.zhn.film.controller.cinema.vo.response.HalltypeResponseVO;

import java.util.List;

public interface CinemaServiceAPI {

    /**
     * 查询影院列表
     * @param cinemaRequestVO
     * @return
     */
    Page<CinemaVO> describeCinemaList(CinemaRequestVO cinemaRequestVO);

    /**
     * @param id
     * @param type
     * @return
     */
    boolean checkCondition(Integer id, String type);

    /**
     * 查询行政区域列表
     * @param areaId
     * @return
     */
    List<AreaResponseVO> describeAreaList(int areaId);

    /**
     * 查询品牌列表
     * @param brandId
     * @return
     */
    List<BrandResponseVO> describeBrandList(int brandId);

    /**
     * 查询影厅列表
     * @param hallTypeId
     * @return
     */
    List<HalltypeResponseVO> describeHallTypeList(int hallTypeId);

    /**
     * 根据影院编号查询影院信息
     * @param cinemaId
     * @return
     */
    CinemaResponseVO describeCinemaByCinemaId(String cinemaId);

    /**
     * 根据影院编号查询播放场次列表
     * @param cinemaId
     * @return
     */
    List<CinemaFilmVO> describeCinemaFilmListByCinemaId(String cinemaId);

    /**
     * 根据场次编号查询播放信息
     * @param fieldId
     * @return
     */
    CinemaFilmInfoVO describeCinemaFilmInfoByFieldId(String fieldId);

    /**
     * 根据场次编号查询放映信息
     * @param fieldId
     * @return
     */
    FieldHallInfoVO describeFieldHallInfoByFieldId(String fieldId);

}
