package org.launchcode.buildMyApptriangle.models;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class Contract extends AbstractEntity{




    @NotNull
    @Size(min =1, max =255)
    private String jobDescription;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    //import name from Employee


    public Contract(String jobDescription){
        this.jobDescription = jobDescription;
    }

    public Contract(){}

    public String getJobDescription(){
        return jobDescription;
    }

    public void setJobDescription(String jobDescription){
        this.jobDescription=jobDescription;
    }

    public Employee getEmployee(){
        return employee;
    }

    public void setEmployee(Employee employee){
        this.employee= employee;
    }
}
