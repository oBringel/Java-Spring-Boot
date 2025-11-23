package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControllerLogin {

    @GetMapping("/login")
    public String carregaPaginaListagem(){
        return "seguranca/login";
    }
}
