package com.justin.app.apisecurity.controller;

import com.justin.app.apisecurity.web.authentication.ApiAuthentication;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class TestController {

    @GetMapping(value = "/check/register",produces = MediaType.APPLICATION_JSON_VALUE)
    public String checkRegister(@AuthenticationPrincipal ApiAuthentication authentication){
        log.info("inside check register");
        String principalName = authentication.getName();
      return "ok to register " + principalName;
    }

    @GetMapping(value = "/shares",produces = MediaType.APPLICATION_JSON_VALUE)
    public String shares(@AuthenticationPrincipal ApiAuthentication authentication){
        String principalName = authentication.getName();
        return "ok shares for " + principalName;
    }
}
