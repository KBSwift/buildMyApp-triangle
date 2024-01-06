package org.launchcode.buildMyApptriangle.models;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class Contract {

    private int id;

    private boolean completion;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    //import name from Employee

    //import address from Customer
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isCompletion() {
        return completion;
    }

    public void setCompletion(boolean completion) {
        this.completion = completion;
    }




}
