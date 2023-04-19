package skypro.liberyofhogwarts.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class MainController {

    @GetMapping
    public String getString(){
        return "WebApp is working!";
    }
}
