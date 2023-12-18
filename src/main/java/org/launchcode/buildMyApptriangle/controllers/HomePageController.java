package org.launchcode.buildMyApptriangle.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomePageController {
    @GetMapping("/")
    @ResponseBody
    public String renderHomePage() {
        return "WELCOME TO OUR CRM BUILD MY APP TRIANGLE APPLICATION";
    }
}
