package org.launchcode.buildMyApptriangle.models;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

public class Employee extends AbstractEntity {

    @NotNull
    private String email;

    @NotNull
    @Size(min=1,max=10)
    private String phoneNum;

    private boolean availability;

    @OneToMany
    @JoinColumn(name="employee_id")
    private final List<Contract> contract = new ArrayList<>();

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
