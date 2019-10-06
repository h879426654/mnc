package com.guc;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableAutoConfiguration
@RestController
public class GucController {

    @GetMapping(value ="/test")
    public String test(String a){

        return a;
    }

    public String getMemories(){
    }
}
