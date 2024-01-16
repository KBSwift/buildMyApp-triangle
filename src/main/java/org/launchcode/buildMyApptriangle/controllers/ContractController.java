package org.launchcode.buildMyApptriangle.controllers;

import jakarta.validation.Valid;
import org.launchcode.buildMyApptriangle.models.Contract;
import org.launchcode.buildMyApptriangle.models.Customer;
import org.launchcode.buildMyApptriangle.models.data.ContractRepository;
import org.launchcode.buildMyApptriangle.models.data.CustomerRepository;
import org.launchcode.buildMyApptriangle.models.data.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("contracts")
public class ContractController {

    @Autowired
    private ContractRepository contractRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("contracts", contractRepository.findAll());
        return "contracts/index";
    }

    @GetMapping("add")
    public String displayAddContractForm(Model model) {
        model.addAttribute("customers", customerRepository.findAll());
        model.addAttribute("employees", employeeRepository.findAll());
        model.addAttribute(new Contract());
        return "contracts/add";
    }

    @PostMapping("add")
    public String processAddContractForm(@ModelAttribute @Valid Contract newContract,
                                         Errors errors) {
        if (errors.hasErrors()) {
            return "contracts/add";
        }
        else {
            contractRepository.save(newContract);
        }
        return "redirect:";
    }

    @GetMapping("delete")
    public String displayDeleteContractForm(Model model) {
        model.addAttribute("contracts", contractRepository.findAll());
        return "contracts/delete";
    }

    @PostMapping("delete")
    public String processDeleteContractForm(@ModelAttribute @Valid Contract deleteContract) {
        contractRepository.deleteById(deleteContract.getId());
        return "redirect:";
    }
}
