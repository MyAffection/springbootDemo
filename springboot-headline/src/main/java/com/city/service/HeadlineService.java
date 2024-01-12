package com.city.service;

import com.city.pojo.Headline;
import com.baomidou.mybatisplus.extension.service.IService;
import com.city.pojo.vo.PortalVo;
import com.city.utils.Result;

/**
* @author heyua
* @description 针对表【news_headline】的数据库操作Service
* @createDate 2024-01-09 20:44:34
*/
public interface HeadlineService extends IService<Headline> {

    Result findNewPage(PortalVo portalVo);

    Result showHeadLineDetail(Integer hid);

    Result publishHeadlish(Headline headline, String token);

    Result findHeadline(Integer hid);

    Result updateData(Headline headline);

    Result delectById(Integer hid);
}
