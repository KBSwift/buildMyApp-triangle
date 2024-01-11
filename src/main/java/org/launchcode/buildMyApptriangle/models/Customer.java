package org.launchcode.buildMyApptriangle.models;


import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;


import jakarta.persistence.*;

import java.util.List;

//Adding Spring Notation according to database
@Entity
public class Customer extends AbstractEntity {
    @Id
    @GeneratedValue
    private int customerId;


    private String customerName;


    @NotNull
    private String customerEmail;

    @NotNull
    @Size(min =1,max = 10)
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
    public List<Contract> getContractList(){
        return contractList;
    }

    public void setContractList(List<Contract> contractList){
        this.contractList=contractList;
    }
}
