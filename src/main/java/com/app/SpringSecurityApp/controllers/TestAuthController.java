package com.app.SpringSecurityApp.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class TestAuthController {
    
    @GetMapping("/hello")
    public ResponseEntity<String> hello(){
        return new ResponseEntity<>("Hello world!",HttpStatus.OK);
    }

    @GetMapping("/hello-secured")
    public ResponseEntity<String> helloSecured(){
        return new ResponseEntity<>("Hello world! <Secured>",HttpStatus.OK);
    }
}
