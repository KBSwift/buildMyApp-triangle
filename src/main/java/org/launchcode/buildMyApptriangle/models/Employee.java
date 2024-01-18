package org.launchcode.buildMyApptriangle.models;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Entity
public class Employee extends AbstractUser{
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
  /*  @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private List<Contract> contracts;*/

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
}