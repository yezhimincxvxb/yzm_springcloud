package com.yzm.gateway.limit.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @RequestMapping("/fallback")
    public String fallback() {
        System.out.println("fallback");
        return "local fallback";
    }

}
