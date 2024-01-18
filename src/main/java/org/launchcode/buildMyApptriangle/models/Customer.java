package org.launchcode.buildMyApptriangle.models;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity
public class Customer extends AbstractUser implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToMany
    @JoinTable(
            name = "customers_roles",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name= "role_id", referencedColumnName = "id"))
    private Collection<Role> customerRoles;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Contract> contracts;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(() -> "read");
    }

    public Customer() {

    }

    public Customer(String username, String password, String firstName, String lastName, Collection<Role> customerRoles) {
        super(username, password, firstName, lastName);
        this.customerRoles = customerRoles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Collection<Role> getCustomerRoles() {
        return customerRoles;
    }

    public void setCustomerRoles(Collection<Role> customerRoles) {
        this.customerRoles = customerRoles;
    }

    public List<Contract> getContracts() {
        return contracts;
    }

    public void setContracts(List<Contract> contracts) {
        this.contracts = contracts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(id, customer.id) && Objects.equals(customerRoles, customer.customerRoles) && Objects.equals(contracts, customer.contracts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, customerRoles, contracts);
    }
}
