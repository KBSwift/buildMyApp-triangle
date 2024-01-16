package org.launchcode.buildMyApptriangle.controllers;

import jakarta.validation.Valid;
import org.launchcode.buildMyApptriangle.models.Contract;
import org.launchcode.buildMyApptriangle.models.data.ContractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("contracts")
public class ContractController {

    @Autowired
    private ContractRepository contractRepository;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("contracts", contractRepository.findAll());
        return "contracts/index";
    }

    @GetMapping("add")
    public String displayAddContractForm(Model model) {
        model.addAttribute(new Contract());
        return "contracts/add";
    }

    @PostMapping("add")
    public String processAddContractForm(@ModelAttribute @Valid Contract newContract,
                                         Errors errors, Model model) {
        if (errors.hasErrors()) {
            return "contracts/add";
        }
        else {
            contractRepository.save(newContract);
        }
        return "redirect:";
    }

    @GetMapping("delete")
    public String displayDeleteContractForm() {
        return "contracts/delete";
    }

//    @GetMapping("view/{contractId}")
}
