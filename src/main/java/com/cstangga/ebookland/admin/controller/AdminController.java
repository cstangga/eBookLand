package com.cstangga.ebookland.admin.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@org.springframework.stereotype.Controller

@RequestMapping("/admin")
public class AdminController {
    @GetMapping
    public void adminBoard() {
        log.info("GET /admin/adminboard");
    }
}
