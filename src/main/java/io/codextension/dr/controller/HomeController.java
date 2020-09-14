package io.codextension.dr.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/")
public class HomeController {

    @GetMapping(value = "user", produces = "application/json")
    public Map<String, Object> getClaimsFromBean() {// https://graph.facebook.com/10164023071050164/picture?type=large
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return Collections.emptyMap();
    }
}