package com.city.controller;

import com.city.pojo.vo.PortalVo;
import com.city.service.HeadlineService;
import com.city.service.TypeService;
import com.city.utils.Result;
import io.jsonwebtoken.impl.crypto.RsaProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("portal")
@CrossOrigin
public class TypesController {
    @Autowired
    private TypeService typeService;

    @Autowired
    private HeadlineService headlineService;

    //获得类别
    @GetMapping("findAllTypes")
    public Result findAll() {
        Result result = typeService.findAllTypes();
        return result;

    }


    //分页查询
    @PostMapping("findNewsPage")
    public Result findPage(@RequestBody PortalVo portalVo) {

        Result result = headlineService.findNewPage(portalVo);
        return result;
    }


    @PostMapping("showHeadlineDetail")
    public Result showHeadLineDetail(Integer hid) {

        Result result = headlineService.showHeadLineDetail(hid);

        return result;
    }


}
