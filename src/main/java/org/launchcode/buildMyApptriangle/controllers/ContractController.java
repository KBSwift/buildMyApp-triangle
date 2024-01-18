package org.launchcode.buildMyApptriangle.controllers;

import jakarta.validation.Valid;
import org.launchcode.buildMyApptriangle.models.Contract;
import org.launchcode.buildMyApptriangle.models.Customer;
import org.launchcode.buildMyApptriangle.models.Employee;
import org.launchcode.buildMyApptriangle.models.data.ContractRepository;
import org.launchcode.buildMyApptriangle.models.data.CustomerRepository;
import org.launchcode.buildMyApptriangle.models.data.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
    public String index(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        model.addAttribute("contracts", contractRepository.findAll());

        // Check what kind of user the account is, then pass in the current user object as a model.
        if (employeeRepository.findEmployeeByUsername(userDetails.getUsername()).isEmpty() && customerRepository.findCustomerByUsername(userDetails.getUsername()).isEmpty()) {
            throw new UsernameNotFoundException("User not present");
        }
        else if (employeeRepository.findEmployeeByUsername(userDetails.getUsername()).isEmpty()) {
            Customer user = customerRepository.findCustomerByUsername(userDetails.getUsername())
                    .orElseThrow(() -> new UsernameNotFoundException("Customer not present"));
            model.addAttribute("user", user);
        }
        else {
            Employee user = employeeRepository.findEmployeeByUsername(userDetails.getUsername())
                    .orElseThrow(() -> new UsernameNotFoundException("Employee not present"));
            model.addAttribute("user", user);
        }
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
        return "redirect:/contracts/";
    }

    @GetMapping("delete")
    public String displayDeleteContractForm(Model model) {
        model.addAttribute("contracts", contractRepository.findAll());
        return "contracts/delete";
    }

    @PostMapping("delete")
    public String processDeleteContractForm(@ModelAttribute @Valid Contract deleteContract) {
        contractRepository.deleteById(deleteContract.getId());
        return "redirect:/contracts/";
    }

    @GetMapping("view/{id}")
    public String displayViewContract(Model model, @PathVariable Long id) {
        Optional optionalContract = contractRepository.findById(id);
        if (optionalContract.isPresent()) {
            Contract contract = (Contract) optionalContract.get();
            model.addAttribute("contract", contract);
            return "contracts/view";
        } else {
            return "redirect:/contracts/";
        }
    }

    @GetMapping("view/{id}/update")
    public String displayUpdateContract(Model model, @PathVariable Long id) {
        Optional optionalContract = contractRepository.findById(id);
        if (optionalContract.isPresent()) {
            Contract contract = (Contract) optionalContract.get();
            model.addAttribute("contract", contract);
            model.addAttribute("employees", employeeRepository.findAll());
            model.addAttribute("customers", customerRepository.findAll());
            return "contracts/update";
        } else {
            return "redirect:/contracts/";
        }
    }

    @PostMapping("view/{id}/update")
    public String processUpdateContract(Model model, @PathVariable Long id, @ModelAttribute @Valid Contract contract,
                                        Errors errors) {
        if (errors.hasErrors()) {
            return "view/{id}/update";
        }
        else {
            contractRepository.save(contract);
        }
        return "redirect:/contracts/view/" + id;
    }
}
