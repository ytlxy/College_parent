package com.at.cmsservice.service.impl;

import com.at.cmsservice.entity.CrmBanner;
import com.at.cmsservice.entity.vo.BannerQuery;
import com.at.cmsservice.mapper.CrmBannerMapper;
import com.at.cmsservice.service.CrmBannerService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mysql.jdbc.StringUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务实现类
 * </p>
 *
 * @author :)
 * @since 2021-05-21
 */
@Service
public class CrmBannerServiceImpl extends ServiceImpl<CrmBannerMapper, CrmBanner> implements CrmBannerService {

    @Cacheable(key = "'selectIndexList'",value = "banner")
    @Override
    public List<CrmBanner> selectAllBanner() {
        QueryWrapper<CrmBanner> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        queryWrapper.last("limit 2");
        List<CrmBanner> crmBanners = baseMapper.selectList(queryWrapper);
        return crmBanners;
    }

    @Override
    public void pageQuery(Page<CrmBanner> page, BannerQuery query) {
        QueryWrapper<CrmBanner> wrapper = new QueryWrapper<>();
        if (query != null) {
            String name = query.getName();
            String begin = query.getBegin();
            String end = query.getEnd();
            if (!StringUtils.isEmptyOrWhitespaceOnly(name)) {
                wrapper.like("title", name);
            }
            if (!StringUtils.isEmptyOrWhitespaceOnly(begin)) {
                wrapper.ge("gmt_create", begin);
            }
            if (!StringUtils.isEmptyOrWhitespaceOnly(end)){
                wrapper.le("gmt_modified",end);
            }
        }
        wrapper.orderByDesc("gmt_create");
        baseMapper.selectPage(page,wrapper);
    }
}
