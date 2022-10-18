package com.example.demo.controller.test;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping
@CrossOrigin(origins = "http://localhost:8080", allowedHeaders = "*")
public class test {
    @PostMapping("/test")
    public void test (@RequestBody AvailableRoomRequest availableRoomRequest) {
        log.info("***" + availableRoomRequest);

    }
}
