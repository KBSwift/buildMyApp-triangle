package org.launchcode.buildMyApptriangle.controllers;

import org.launchcode.buildMyApptriangle.models.EmailForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EmailController {

    @GetMapping("/contact")
    public String showForm(Model model){
        EmailForm emailForm = new EmailForm();
        model.addAttribute("emailForm", emailForm);

        return "contact_form";
    }
}
