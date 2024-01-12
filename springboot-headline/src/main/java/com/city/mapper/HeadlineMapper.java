package com.city.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.city.pojo.Headline;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.city.pojo.vo.PortalVo;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * @author heyua
 * @description 针对表【news_headline】的数据库操作Mapper
 * @createDate 2024-01-09 20:44:34
 * @Entity com.city.pojo.Headline
 */
public interface HeadlineMapper extends BaseMapper<Headline> {
    IPage<Map> selectPageMap(IPage page, @Param("portalVo") PortalVo portalVo);

    Map selectDetailMap(Integer hid);
}




