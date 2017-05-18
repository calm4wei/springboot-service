package com.zqykj.service.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by fengwei on 17/5/18.
 */
@RestController
@RequestMapping("/api/v1")
public class DemoController {

    @RequestMapping("/index")
    public String index() {
        return "Greetings from Spring Boot!";
    }

}
