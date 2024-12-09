package com.jpmc.midascore.restapi;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestApiApplication {

    @GetMapping("/")
    public String index(@RequestParam(value = "name", defaultValue = "World") String name) { return "hello" + name; }

    public String
}
