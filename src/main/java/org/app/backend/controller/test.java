package org.app.backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/test")
public class test {
    @GetMapping
    public String toString() {
        return "hello";
    }
}
