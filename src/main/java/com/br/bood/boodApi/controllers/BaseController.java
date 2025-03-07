package com.br.bood.boodApi.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class BaseController {

    @GetMapping
    public ResponseEntity<String> basePath() {
        return ResponseEntity.ok("bood Spring Boot API");
    }
}
