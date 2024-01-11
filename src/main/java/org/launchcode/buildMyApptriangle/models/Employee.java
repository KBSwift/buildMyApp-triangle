package org.launchcode.buildMyApptriangle.models;


import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Employee extends AbstractEntity  {
    @Id
    @GeneratedValue
    private int id;




    @NotNull
    private String email;

    @NotNull
    @Size(min=1,max=10)
    private String phoneNum;

    private boolean availability;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private List<Contract> contracts;

//import address for site form Customer

    public Employee(String email, String phoneNum, boolean availability) {
        this.email = email;
        this.phoneNum = phoneNum;
        this.availability = availability;
    }

    public Employee(){}

    public String getEmail() {
        return email;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public boolean isAvailability() {
        return availability;
    }
}
