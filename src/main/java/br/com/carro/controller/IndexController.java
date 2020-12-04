package br.com.carro.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class IndexController {

    @GetMapping
    public String get() {
        return "Get Spring Boot";
    }

    @GetMapping("/login/{login}/senha/{senha}")
    public String login( @PathVariable("login") String login,  @PathVariable("senha") String senha) {
        return "Login: " + login + " Senha: " + senha;
    }


}
