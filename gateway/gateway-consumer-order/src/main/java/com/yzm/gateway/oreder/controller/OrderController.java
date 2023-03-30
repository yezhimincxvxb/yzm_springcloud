package com.yzm.gateway.oreder.controller;

import com.yzm.commons.api.CommonResult;
import com.yzm.gateway.oreder.feign.OrderFeign;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Resource
    private OrderFeign orderFeign;

    @GetMapping("/hello")
    public CommonResult<String> hello() {
        return orderFeign.hello();
    }

    @GetMapping("/timeout/{millis}")
    public CommonResult<String> timeout(@PathVariable("millis") long millis) throws InterruptedException {
        return orderFeign.timeout(millis);
    }

    @GetMapping("/getById/{id}")
    public CommonResult<String> getById(@PathVariable("id") Integer id) {
        return orderFeign.getById(id);
    }


    //============================================= 断言 =============================================

    @PostMapping("/post")
    public CommonResult<String> post() {
        return CommonResult.success("post");
    }

    @GetMapping("/time")
    public CommonResult<String> time() {
        return CommonResult.success("time");
    }

    @GetMapping("/header")
    public CommonResult<String> header(HttpServletRequest request) {
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String name = headerNames.nextElement();
            System.out.println(name + " = " + request.getHeader(name));
        }
        return CommonResult.success("header");
    }

    @GetMapping("/cookie")
    public CommonResult<String> cookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            System.out.println(cookie.getName() + " = " + cookie.getValue());
        }
        return CommonResult.success("cookie");
    }

    @GetMapping("/host")
    public CommonResult<String> host(HttpServletRequest request) {
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String name = headerNames.nextElement();
            System.out.println(name + " = " + request.getHeader(name));
        }
        return CommonResult.success("host");
    }

    @GetMapping("/query")
    public CommonResult<String> query(Integer number) {
        System.out.println(number);
        return CommonResult.success("query");
    }

    //============================================= 重试 =============================================

    @GetMapping("/retry/{id}")
    public CommonResult<String> retry(@PathVariable("id") int id) {
        if (id < 0) throw new IllegalArgumentException("参数错误");
        return CommonResult.success("retry");
    }

    //============================================= 熔断 =============================================

    @GetMapping("/fallback")
    public CommonResult<String> fallback() {
        return CommonResult.success("order fallback");
    }

}
