package com.at.cmsservice.service;

import com.at.cmsservice.entity.CrmBanner;
import com.at.cmsservice.entity.vo.BannerQuery;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务类
 * </p>
 *
 * @author :)
 * @since 2021-05-21
 */
public interface CrmBannerService extends IService<CrmBanner> {

    public List<CrmBanner> selectAllBanner();

    public void pageQuery(Page<CrmBanner> page, BannerQuery query);

}
