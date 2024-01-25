
package org.launchcode.buildMyApptriangle.models;

import jakarta.persistence.*;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Objects;

@Entity
public class Contract{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Size(min=1, max=255)
    private String name;

    @NotNull
    @Size(min=1, max=255)
    private String address;

    @NotNull
    @Size(min =1, max =255)
    private String jobDescription;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;


    //TODO: Would this be ManyToMany? Multiple employees might work on the same job.
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    public Contract() {

    }

    public Contract(String name, String address, String jobDescription, Customer customer, Employee employee) {
        this.name = name;
        this.address = address;
        this.jobDescription = jobDescription;
        this.customer = customer;
        this.employee = employee;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}

