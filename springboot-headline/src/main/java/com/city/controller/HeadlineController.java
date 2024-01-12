package com.city.controller;

import com.city.pojo.Headline;
import com.city.service.HeadlineService;
import com.city.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("headline")
@CrossOrigin
public class HeadlineController {

    @Autowired
    private HeadlineService headlineService;

    //登录
    @PostMapping("publish")
    public Result publish(@RequestBody Headline headline, @RequestHeader String token) {


        Result result = headlineService.publishHeadlish(headline, token);


        return result;
    }

    //详情信息
    @PostMapping("findHeadLineByHid")
    public Result findHeadline(Integer hid) {

        Result result = headlineService.findHeadline(hid);

        return result;

    }

    @PostMapping("update")
    public Result updateData(@RequestBody Headline headline) {

        Result result = headlineService.updateData(headline);

        return result;
    }


    @PostMapping("removeByHid")
    public Result deleteById(Integer hid) {


        Result result = headlineService.delectById(hid);
        return result;

    }
}
