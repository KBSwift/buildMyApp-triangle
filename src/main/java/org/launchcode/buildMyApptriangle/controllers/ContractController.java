package org.launchcode.buildMyApptriangle.controllers;

import org.launchcode.buildMyApptriangle.models.data.ContractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
    public String displayAddContractForm() {
        return "contracts/add";
    }

    @GetMapping("delete")
    public String displayDeleteContractForm() {
        return "contracts/delete";
    }

//    @GetMapping("view/{contractId}")
}
