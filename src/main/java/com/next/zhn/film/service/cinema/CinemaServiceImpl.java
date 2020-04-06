package com.next.zhn.film.service.cinema;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.next.zhn.film.controller.cinema.vo.CinemaFilmInfoVO;
import com.next.zhn.film.controller.cinema.vo.CinemaFilmVO;
import com.next.zhn.film.controller.cinema.vo.CinemaVO;
import com.next.zhn.film.controller.cinema.vo.FieldHallInfoVO;
import com.next.zhn.film.controller.cinema.vo.request.CinemaRequestVO;
import com.next.zhn.film.controller.cinema.vo.response.AreaResponseVO;
import com.next.zhn.film.controller.cinema.vo.response.BrandResponseVO;
import com.next.zhn.film.controller.cinema.vo.response.CinemaResponseVO;
import com.next.zhn.film.controller.cinema.vo.response.HalltypeResponseVO;
import com.next.zhn.film.dao.entity.FilmAreaDictT;
import com.next.zhn.film.dao.entity.FilmBrandDictT;
import com.next.zhn.film.dao.entity.FilmCinemaT;
import com.next.zhn.film.dao.entity.FilmHallDictT;
import com.next.zhn.film.dao.mapper.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("CinemaSrevice")
public class CinemaServiceImpl implements CinemaServiceAPI {

    @Autowired
    private FilmCinemaTMapper cinemaTMapper;
    @Autowired
    private FilmAreaDictTMapper areaDictTMapper;
    @Autowired
    private FilmFieldTMapper fieldTMapper;
    @Autowired
    private FilmBrandDictTMapper brandDictTMapper;
    @Autowired
    private FilmHallDictTMapper hallDictTMapper;
    @Autowired
    private FilmHallFilmInfoTMapper hallFilmInfoTMapper;

    @Override
    public Page<CinemaVO> describeCinemaList(CinemaRequestVO cinemaRequestVO) {

        Page<FilmCinemaT> page = new Page<>(
                cinemaRequestVO.getNowPage(),
                cinemaRequestVO.getPageSize());

        QueryWrapper<FilmCinemaT> queryWrapper = cinemaRequestVO.getQueryWrapper();

        IPage<FilmCinemaT> filmCinemaTIPage = cinemaTMapper.selectPage(page, queryWrapper);

        Page<CinemaVO> cinemaVOPage = new Page<>(
                cinemaRequestVO.getNowPage(),
                cinemaRequestVO.getPageSize(),
                filmCinemaTIPage.getTotal());

        List<CinemaVO> cinemaVOList = filmCinemaTIPage.getRecords().stream().map((data) -> {
            CinemaVO cinemaVO = new CinemaVO();
            cinemaVO.setAddress(data.getCinemaAddress());
            cinemaVO.setCinemaName(data.getCinemaName());
            cinemaVO.setMinimumPrice(data.getMinimumPrice() + "");
            cinemaVO.setUuid(data.getUuid() + "");
            return cinemaVO;
        }).collect(Collectors.toList());

        cinemaVOPage.setRecords(cinemaVOList);

        return cinemaVOPage;
    }

    @Override
    public boolean checkCondition(Integer id, String type) {
        switch (type) {
            case "area":
                FilmAreaDictT filmAreaDictT = areaDictTMapper.selectById(id);
                if (filmAreaDictT != null && filmAreaDictT.getUuid() != null) {
                    return true;
                }
                break;
            case "hallType":
                FilmHallDictT filmHallDictT = hallDictTMapper.selectById(id);
                if (filmHallDictT != null && filmHallDictT.getUuid() != null) {
                    return true;
                }
                break;
            case "brand":
                FilmBrandDictT filmBrandDictT = brandDictTMapper.selectById(id);
                if (filmBrandDictT != null && filmBrandDictT.getUuid() != null) {
                    return true;
                }
                break;
                default:break;
        }
        return false;
    }

    @Override
    public List<AreaResponseVO> describeAreaList(final int areaId) {
        List<FilmAreaDictT> filmAreaDictTS = areaDictTMapper.selectList(null);
        List<AreaResponseVO> list = filmAreaDictTS.stream().map((data) -> {
            AreaResponseVO areaResponseVO = new AreaResponseVO();
            if (data.getUuid().equals(areaId)) {
                areaResponseVO.setIsActive(true);
            }
            areaResponseVO.setAreaId(data.getUuid() + "");
            areaResponseVO.setAreaName(data.getShowName());
            return areaResponseVO;
        }).collect(Collectors.toList());
        return list;
    }

    @Override
    public List<BrandResponseVO> describeBrandList(final int brandId) {
        List<FilmBrandDictT> filmAreaDictTS = brandDictTMapper.selectList(null);
        List<BrandResponseVO> list = filmAreaDictTS.stream().map((data) -> {
            BrandResponseVO brandResponseVO = new BrandResponseVO();
            if (data.getUuid().equals(brandId)) {
                brandResponseVO.setIsActive(true);
            }
            brandResponseVO.setBrandId(data.getUuid() + "");
            brandResponseVO.setBrandName(data.getShowName());
            return brandResponseVO;
        }).collect(Collectors.toList());
        return list;
    }

    @Override
    public List<HalltypeResponseVO> describeHallTypeList(int hallTypeId) {
        List<FilmHallDictT> filmAreaDictTS = hallDictTMapper.selectList(null);
        List<HalltypeResponseVO> list = filmAreaDictTS.stream().map((data) -> {
            HalltypeResponseVO halltypeResponseVO = new HalltypeResponseVO();
            if (data.getUuid().equals(hallTypeId)) {
                halltypeResponseVO.setIsActive(true);
            }
            halltypeResponseVO.setHalltypeId(data.getUuid() + "");
            halltypeResponseVO.setHalltypeName(data.getShowName());
            return halltypeResponseVO;
        }).collect(Collectors.toList());
        return list;
    }

    @Override
    public CinemaResponseVO describeCinemaByCinemaId(String cinemaId) {
        CinemaResponseVO cinemaResponseVO = new CinemaResponseVO();
        FilmCinemaT cinemaT = cinemaTMapper.selectById(cinemaId);
        cinemaResponseVO.setCinemaId(cinemaT.getUuid());
        cinemaResponseVO.setImgUrl(cinemaT.getImgAddress());
        BeanUtils.copyProperties(cinemaT, cinemaResponseVO);
        return cinemaResponseVO;
    }

    @Override
    public List<CinemaFilmVO> describeCinemaFilmListByCinemaId(String cinemaId) {
        List<CinemaFilmVO> cinemaFilmVOS = fieldTMapper.describeFieldListByCinemaId(cinemaId);
        return cinemaFilmVOS;
    }

    @Override
    public CinemaFilmInfoVO describeCinemaFilmInfoByFieldId(String fieldId) {
        CinemaFilmInfoVO cinemaFilmInfoVO = fieldTMapper.describeFieldInfoByFildId(fieldId);
        return cinemaFilmInfoVO;
    }

    @Override
    public FieldHallInfoVO describeFieldHallInfoByFieldId(String fieldId) {
        FieldHallInfoVO fieldHallInfoVO = fieldTMapper.describeHallInfoByFildId(fieldId);
        return fieldHallInfoVO;
    }
}
