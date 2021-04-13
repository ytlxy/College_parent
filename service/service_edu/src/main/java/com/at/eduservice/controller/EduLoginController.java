package com.at.eduservice.controller;

import com.at.commonUtils.R;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/eduservice/user")
public class EduLoginController {

    @PostMapping("/login")
    public R login(){
        return R.ok().data("token","admin");
    }

    @GetMapping("/info")
    public R info(){
     return R.ok().data("roles","[admin]").data("name","admin").data("avatar","https://z3.ax1x.com/2021/04/01/cVK1MV.jpg");
    }

}
