package org.launchcode.buildMyApptriangle.models;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class Customer extends AbstractEntity{


    @NotNull
    private String customerEmail;

    @NotNull
    @Size(min =1,max = 10)
    private String customerPhoneNumber;




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


}
