package org.launchcode.buildMyApptriangle.models;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity
public class Employee extends AbstractUser implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToMany
    @JoinTable(
            name = "employees_roles",
            joinColumns = @JoinColumn(
                    name = "employee_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name= "role_id", referencedColumnName = "id"))
    private Collection<Role> employeeRoles;


    //TODO: Would this be ManyToMany? Multiple employees might work on the same job.
    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private List<Contract> contracts;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(() -> "read");
    }

    public Employee() {
    }

    public Employee( String username, String password, String firstName, String lastName, Collection<Role> employeeRoles) {
        super(username, password, firstName, lastName);
        this.employeeRoles = employeeRoles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Collection<Role> getEmployeeRoles() {
        return employeeRoles;
    }

    public void setEmployeeRoles(Collection<Role> employeeRoles) {
        this.employeeRoles = employeeRoles;
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
        if (!(o instanceof Employee employee)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(id, employee.id) && Objects.equals(employeeRoles, employee.employeeRoles) && Objects.equals(contracts, employee.contracts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, employeeRoles, contracts);
    }
}