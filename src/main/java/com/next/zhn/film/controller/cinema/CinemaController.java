package com.next.zhn.film.controller.cinema;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.next.zhn.film.config.properties.FilmProperties;
import com.next.zhn.film.controller.cinema.vo.CinemaFilmInfoVO;
import com.next.zhn.film.controller.cinema.vo.CinemaFilmVO;
import com.next.zhn.film.controller.cinema.vo.CinemaVO;
import com.next.zhn.film.controller.cinema.vo.FieldHallInfoVO;
import com.next.zhn.film.controller.cinema.vo.request.CinemaRequestVO;
import com.next.zhn.film.controller.cinema.vo.request.FieldRequestVO;
import com.next.zhn.film.controller.cinema.vo.response.AreaResponseVO;
import com.next.zhn.film.controller.cinema.vo.response.BrandResponseVO;
import com.next.zhn.film.controller.cinema.vo.response.CinemaResponseVO;
import com.next.zhn.film.controller.cinema.vo.response.HalltypeResponseVO;
import com.next.zhn.film.controller.common.BaseResponseVO;
import com.next.zhn.film.service.cinema.CinemaServiceAPI;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author 张浩男
 */
@RestController
@Api("影院模块相关的api")
@RequestMapping(value = "/cinema")
public class CinemaController {

    @Autowired
    private FilmProperties filmProperties;
    @Autowired
    private CinemaServiceAPI cinemaServiceAPI;

    @ApiOperation(value = "获取播放场次", notes = "获取播放场次")
    @ApiImplicitParam(name = "cinemaId",
            value = "影院编号", paramType = "GET", required = true, dataType = "string")
    @RequestMapping(value = "/getFields",method = RequestMethod.GET)
    public BaseResponseVO getFields(String cinemaId){
        CinemaResponseVO cinemaInfo = cinemaServiceAPI.describeCinemaByCinemaId(cinemaId);

        List<CinemaFilmVO> cinemaFilmVOS = cinemaServiceAPI.describeCinemaFilmListByCinemaId(cinemaId);
        List<Map<String,CinemaFilmVO>> filmList = Lists.newArrayList();
        cinemaFilmVOS.stream().forEach(filmInfo->{
            Map<String,CinemaFilmVO> map = Maps.newHashMap();
            map.put("filmInfo",filmInfo);
            filmList.add(map);
        });

        Map<String,Object> result = Maps.newHashMap();
        result.put("cinemaInfo",cinemaInfo);
        result.put("filmList",filmList);
        return BaseResponseVO.success(filmProperties.getImgPre(),result);
    }

    @ApiOperation(value = "获取播放场次详情", notes = "获取播放场次详情")
    @ApiImplicitParam(name = "fieldRequestVO",
            value = "请求数据", paramType = "POST", required = true, dataType = "Object")
    @RequestMapping(value = "/getFieldInfo",method = RequestMethod.POST)
    public BaseResponseVO getFieldInfo(@RequestBody FieldRequestVO fieldRequestVO){
        CinemaResponseVO cinemaInfo = cinemaServiceAPI.describeCinemaByCinemaId(fieldRequestVO.getCinemaId());
        CinemaFilmInfoVO filmInfo = cinemaServiceAPI.describeCinemaFilmInfoByFieldId(fieldRequestVO.getFieldId());
        FieldHallInfoVO hallInfo = cinemaServiceAPI.describeFieldHallInfoByFieldId(fieldRequestVO.getFieldId());
        Map<String,Object> result = Maps.newHashMap();
        result.put("hallInfo",hallInfo);
        result.put("filmInfo",filmInfo);
        result.put("cinemaInfo",cinemaInfo);
        return BaseResponseVO.success(filmProperties.getImgPre(),result);
    }

    @ApiOperation(value = "查询影院列表", notes = "查询影院列表")
    @ApiImplicitParam(name = "requestVO",
            value = "请求数据", paramType = "POST", required = true, dataType = "Object")
    @RequestMapping(value = "/getCinemas",method = RequestMethod.POST)
    public BaseResponseVO getCinemas(@RequestBody CinemaRequestVO requestVO){
        Page<CinemaVO> cinemaVOPage = cinemaServiceAPI.describeCinemaList(requestVO);
        Map<String,Object> result = Maps.newHashMap();
        result.put("cinemas",cinemaVOPage.getRecords());
        return BaseResponseVO.success(cinemaVOPage.getCurrent(),cinemaVOPage.getPages(),result);
    }

    @ApiOperation(value = "影院列表查询条件", notes = "影院列表查询条件")
    @ApiImplicitParam(name = "requestVO",
            value = "请求数据", paramType = "GET", required = true, dataType = "Object")
    @RequestMapping(value = "/getCondition",method = RequestMethod.GET)
    public BaseResponseVO getCondition(@RequestParam(name = "brandId",required = false,defaultValue = "99") Integer brandId,
                                       @RequestParam(name = "hallType",required = false,defaultValue = "99") Integer hallType,
                                       @RequestParam(name = "areaId",required = false,defaultValue = "99") Integer areaId
                                       ){

        if(!cinemaServiceAPI.checkCondition(areaId,"area")){
            areaId = 99;
        }
        if(!cinemaServiceAPI.checkCondition(brandId,"brand")){
            brandId = 9;
        }
        if(!cinemaServiceAPI.checkCondition(hallType,"hallType")){
            hallType = 99;
        }

        List<AreaResponseVO> areaList = cinemaServiceAPI.describeAreaList(areaId);
        List<HalltypeResponseVO> hallTypeList = cinemaServiceAPI.describeHallTypeList(brandId);
        List<BrandResponseVO> brandList = cinemaServiceAPI.describeBrandList(hallType);
        Map<String,Object> result = Maps.newHashMap();
        result.put("areaList",areaList);
        result.put("halltypeList",hallTypeList);
        result.put("brandList",brandList);
        return BaseResponseVO.success(result);
    }

}
