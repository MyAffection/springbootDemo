package com.city.pojo.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class PortalVo {
    @TableId

    private String keyWords;
    private Integer type;
    private Integer pageNum = 1;
    private Integer pageSize = 10;

}
