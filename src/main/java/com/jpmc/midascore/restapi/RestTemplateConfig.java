package com.jpmc.midascore.restapi;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class RestTemplateConfig {

    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
