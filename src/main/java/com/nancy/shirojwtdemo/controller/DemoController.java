package com.nancy.shirojwtdemo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chen
 * @date 2020/6/1 0:00
 */
@RestController
public class DemoController {
    @RequestMapping("hello")
    public String hello() {
        return "hello";
    }
}
