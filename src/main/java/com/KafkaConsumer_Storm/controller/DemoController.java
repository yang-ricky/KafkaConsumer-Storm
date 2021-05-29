package com.KafkaConsumer_Storm.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
    @RequestMapping("/storm")
    public String storm(){
        return "Storm";
    }
}
