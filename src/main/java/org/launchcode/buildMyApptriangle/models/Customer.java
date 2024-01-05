package org.launchcode.buildMyApptriangle.models;

import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;
public class Customer extends AbstractEntity{


    @NotNull
    private String customerEmail;

    @NotNull
    @Size(min =1,max = 10)
    private String customerPhoneNumber;

    @OneToMany(mappedBy = "customer")
    private List<Contract> contractList;

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
