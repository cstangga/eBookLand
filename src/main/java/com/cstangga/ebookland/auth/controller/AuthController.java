package com.cstangga.ebookland.auth.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
@Slf4j
public class AuthController {
    @GetMapping("/login")
    public void login() {
        log.info("GET /auth/login");
    }

}
