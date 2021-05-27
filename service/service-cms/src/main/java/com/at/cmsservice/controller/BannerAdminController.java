package com.at.cmsservice.controller;

import com.at.cmsservice.entity.CrmBanner;
import com.at.cmsservice.entity.vo.BannerQuery;
import com.at.cmsservice.service.CrmBannerService;
import com.at.commonUtils.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 首页banner表 前端控制器
 * </p>
 *
 * @author :)
 * @since 2021-05-21
 */

@RestController
@RequestMapping("/cmsservice/educms")
public class BannerAdminController {
    @Autowired
    private CrmBannerService crmBannerService;

    @PostMapping("/pageBanner/{page}/{limit}")
    public R pageBanner(@PathVariable Long page, @PathVariable Long limit,@RequestBody(required = false) BannerQuery bannerQuery){
        Page<CrmBanner> page1=new Page<>(page,limit);
        crmBannerService.pageQuery(page1,bannerQuery);
        return R.ok().data("items",page1.getRecords()).data("total",page1.getTotal());
    }

    @GetMapping("/get/{id}")
    public R get(@PathVariable String id){
        CrmBanner byId = crmBannerService.getById(id);
        return R.ok().data("item",byId);
    }

    @PostMapping("/addBanner")
    public R addBanner(@RequestBody CrmBanner crmBanner){
        crmBannerService.save(crmBanner);
        return R.ok();
    }

    @PutMapping("/updateById")
    public R updateById(@RequestBody CrmBanner crmBanner){
        crmBannerService.updateById(crmBanner);
        return R.ok();
    }

    @DeleteMapping("/remove/{id}")
    public R remove(@PathVariable String id){
        crmBannerService.removeById(id);
        return R.ok();
    }
}
