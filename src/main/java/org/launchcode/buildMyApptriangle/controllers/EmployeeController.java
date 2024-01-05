package org.launchcode.buildMyApptriangle.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("Employees")
public class EmployeeController {

    @GetMapping("/")
    public String index() {
        return "employees/index";
    }

    @GetMapping("Add")
    public String displayAddEmployeeForm() {
        return "employees/add";
    }

    @GetMapping("Delete")
    public String displayDeleteEmployeeForm() {
        return "employees/delete";
    }

//    @GetMapping("view/{employeeId}")
}