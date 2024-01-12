package com.city.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.city.mapper.HeadlineMapper;
import com.city.pojo.Headline;
import com.city.pojo.vo.PortalVo;
import com.city.service.HeadlineService;
import com.city.utils.JwtHelper;
import com.city.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author heyua
 * @description 针对表【news_headline】的数据库操作Service实现
 * @createDate 2024-01-09 20:44:34
 */

/**
 *
 */
@Service
public class HeadlineServiceImpl extends ServiceImpl<HeadlineMapper, Headline>
        implements HeadlineService {
    @Autowired
    private HeadlineMapper headlineMapper;
    @Autowired
    private JwtHelper jwtHelper;

    @Override
    public Result findNewPage(PortalVo portalVo) {
        IPage<Map> page = new Page<>(portalVo.getPageNum(), portalVo.getPageSize());
        headlineMapper.selectPageMap(page, portalVo);
        List<Map> records = page.getRecords();
        Map Map = new HashMap<>();
        Map.put("pageData", records);
        Map.put("getNum", page.getCurrent());
        Map.put("getSize", page.getSize());
        Map.put("totalPage", page.getPages());
        Map.put("totalSize", page.getTotal());

        Map pageInfo = new HashMap();
        pageInfo.put("pageInfo", Map);

        return Result.ok(pageInfo);
    }

    @Override
    public Result showHeadLineDetail(Integer hid) {
        // 先查询
        Map map = headlineMapper.selectDetailMap(hid);

        if (map != null) {
            Headline headline = new Headline();
            headline.setHid(hid);
            headline.setPageViews((Integer) map.get("pageViews") + 1); // 阅读量+1
            headline.setVersion((Integer) map.get("version")); // 设置版本
            headlineMapper.updateById(headline);

            Map pageInfoMap = new HashMap<>();
            pageInfoMap.put("headline", map);
            return Result.ok(pageInfoMap);
        } else {
            // 处理 map 为 null 的情况，这里可以返回适当的错误信息或采取其他措施。
            return Result.build("null");
        }
    }

    @Override
    public Result publishHeadlish(Headline headline, String token) {

        int userId = jwtHelper.getUserId(token).intValue();
        headline.setPublisher(Integer.valueOf(userId));
        //设置完所有的参数
        headline.setPageViews(0);
        headline.setCreateTime(new Date());
        headline.setUpdateTime(new Date());
        //装配
        headlineMapper.insert(headline);
        return Result.ok("null");
    }

    @Override
    public Result findHeadline(Integer hid) {

        Headline headline = headlineMapper.selectById(hid);

        HashMap Map = new HashMap<>();
        Map.put("headline", headline);
        return Result.ok(Map);
    }

    @Override
    public Result updateData(Headline headline) {


        Integer version = headlineMapper.selectById(headline.getHid()).getVersion();
        headline.setHid(version);
        headline.setUpdateTime(new Date());
        headlineMapper.updateById(headline);

        return Result.ok("修改成功");
    }

    @Override
    public Result delectById(Integer hid) {
        headlineMapper.deleteById(hid);

        return Result.ok("删除成功");
    }


}




