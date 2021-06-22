package com.dd.edu.entity.chapter;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class VideoVo {
    @ApiModelProperty(value = "小节ID")
//    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;


    @ApiModelProperty(value = "小节名称")
    private String title;

}
