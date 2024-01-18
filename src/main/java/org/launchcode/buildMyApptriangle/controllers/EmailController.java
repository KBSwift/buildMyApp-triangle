package org.launchcode.buildMyApptriangle.controllers;

import org.launchcode.buildMyApptriangle.models.EmailForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class EmailController {

    @GetMapping("/contact")
    public String showForm(Model model){
        EmailForm emailForm = new EmailForm();
        model.addAttribute("emailForm", emailForm);

        return "contact_form";
    }

    @PostMapping("/contact")
    public String submitForm(@ModelAttribute("emailForm") EmailForm emailForm){
        System.out.println(emailForm);

        return "contact_success";
    }
}
