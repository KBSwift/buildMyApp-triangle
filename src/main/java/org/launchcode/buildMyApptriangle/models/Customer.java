package org.launchcode.buildMyApptriangle.models;

import jakarta.persistence.*;

import java.util.List;

//Adding Spring Notation according to database
@Entity
public class Customer {
    @Id
    @GeneratedValue
    private int customerId;


    private String customerName;

    private String customerEmail;

    private String customerPhoneNumber;

    private String address;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Contract> contracts;

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerPhoneNumber() {
        return customerPhoneNumber;
    }

    public void setCustomerPhoneNumber(String customerPhoneNumber) {
        this.customerPhoneNumber = customerPhoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Customer(int customerId, String customerName, String customerEmail, String customerPhoneNumber, String address) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.customerPhoneNumber = customerPhoneNumber;
        this.address = address;
    }
}
