package com.at.cmsservice.controller;

import com.at.cmsservice.entity.CrmBanner;
import com.at.cmsservice.service.CrmBannerService;
import com.at.commonUtils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/educms/bannerfront")
public class BannerFrontController {

    @Autowired
    private CrmBannerService crmBannerService;


    @GetMapping("/getAllBanner")
    public R getAllBanner(){
        List<CrmBanner> crmBanners = crmBannerService.selectAllBanner();
        return R.ok().data("list",crmBanners);
    }

}
