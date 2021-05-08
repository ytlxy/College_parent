package com.at.eduservice.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
@ApiModel(value = "课程基本信息",description = "编辑课程基本信息的表单对象")
public class CourseInfoVo {

    @ApiModelProperty(value = "课程id")
    private String id;

    @ApiModelProperty(value = "课程讲师id")
    private String teacherId;

    @ApiModelProperty(value = "课程分类id")
    private String subjectId;

    @ApiModelProperty(value = "课程父级id")
    private String subjectParentId;

    @ApiModelProperty(value = "课程标题")
    private String title;

    @ApiModelProperty(value = "课程消费价格")
    private BigDecimal price;

    @ApiModelProperty(value = "总课时")
    private Integer lessonNum;

    @ApiModelProperty(value = "课程封面图片")
    private String cover;

    @ApiModelProperty(value = "课程简介")
    private String description;

}
