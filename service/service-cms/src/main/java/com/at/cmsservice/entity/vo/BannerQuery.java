package com.at.cmsservice.entity.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class BannerQuery implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private String begin;
    private String end;
}
