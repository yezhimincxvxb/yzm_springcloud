package com.yzm.gateway.oreder.controller;

import com.yzm.commons.api.RespResult;
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
    public RespResult<String> hello() {
        return orderFeign.hello();
    }

    @GetMapping("/timeout/{millis}")
    public RespResult<String> timeout(@PathVariable("millis") long millis) throws InterruptedException {
        return orderFeign.timeout(millis);
    }

    @GetMapping("/getById/{id}")
    public RespResult<String> getById(@PathVariable("id") Integer id) {
        return orderFeign.getById(id);
    }


    //============================================= 断言 =============================================

    @PostMapping("/post")
    public RespResult<String> post() {
        return RespResult.success("post");
    }

    @GetMapping("/time")
    public RespResult<String> time() {
        return RespResult.success("time");
    }

    @GetMapping("/header")
    public RespResult<String> header(HttpServletRequest request) {
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String name = headerNames.nextElement();
            System.out.println(name + " = " + request.getHeader(name));
        }
        return RespResult.success("header");
    }

    @GetMapping("/cookie")
    public RespResult<String> cookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            System.out.println(cookie.getName() + " = " + cookie.getValue());
        }
        return RespResult.success("cookie");
    }

    @GetMapping("/host")
    public RespResult<String> host(HttpServletRequest request) {
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String name = headerNames.nextElement();
            System.out.println(name + " = " + request.getHeader(name));
        }
        return RespResult.success("host");
    }

    @GetMapping("/query")
    public RespResult<String> query(Integer number) {
        System.out.println(number);
        return RespResult.success("query");
    }

    //============================================= 重试 =============================================

    @GetMapping("/retry/{id}")
    public RespResult<String> retry(@PathVariable("id") int id) {
        if (id < 0) throw new IllegalArgumentException("参数错误");
        return RespResult.success("retry");
    }

    //============================================= 熔断 =============================================

    @GetMapping("/fallback")
    public RespResult<String> fallback() {
        return RespResult.success("order fallback");
    }

}
