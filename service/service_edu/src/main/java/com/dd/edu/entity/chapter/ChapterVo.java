package com.dd.edu.entity.chapter;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ChapterVo {
    @ApiModelProperty(value = "章节ID")
//    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;


    @ApiModelProperty(value = "章节名称")
    private String title;
    @ApiModelProperty(value = "小节数组")
    List<VideoVo> children=new ArrayList<>();


}
