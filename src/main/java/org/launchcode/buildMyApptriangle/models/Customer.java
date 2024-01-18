package org.launchcode.buildMyApptriangle.models;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;

@Entity
public class Customer extends AbstractUser{
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
}
