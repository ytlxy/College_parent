package com.at.eduservice.entity.chapter;

import lombok.Data;

import java.io.Serializable;

@Data
public class ViedoVo implements Serializable {
    private static final Long serialVersionUID=1L;
    private String id;
    private String title;
    private Boolean free;

}
