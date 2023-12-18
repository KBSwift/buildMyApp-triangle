package org.launchcode.buildMyApptriangle.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GreetingController {
    @GetMapping("Greeting")
    public String greeting() {
        return "greeting";
    }
}
