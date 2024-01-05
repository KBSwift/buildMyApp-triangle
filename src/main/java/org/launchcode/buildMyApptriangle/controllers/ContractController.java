package org.launchcode.buildMyApptriangle.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("Contracts")
public class ContractController {

    @GetMapping("/")
    public String index() {
        return "contracts/index";
    }

    @GetMapping("Add")
    public String displayAddContractForm() {
        return "contracts/add";
    }

    @GetMapping("Delete")
    public String displayDeleteContractForm() {
        return "contracts/delete";
    }

//    @GetMapping("view/{contractId}")
}
