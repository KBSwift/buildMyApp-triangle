package org.launchcode.buildMyApptriangle.security;

import jakarta.transaction.Transactional;
import org.launchcode.buildMyApptriangle.models.Customer;
import org.launchcode.buildMyApptriangle.models.Employee;
import org.launchcode.buildMyApptriangle.models.Privilege;
import org.launchcode.buildMyApptriangle.models.Role;
import org.launchcode.buildMyApptriangle.models.data.CustomerRepository;
import org.launchcode.buildMyApptriangle.models.data.EmployeeRepository;
import org.launchcode.buildMyApptriangle.models.data.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@Transactional
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (employeeRepository.findEmployeeByUsername(username).isEmpty() && customerRepository.findCustomerByUsername(username).isEmpty()) {
            throw new UsernameNotFoundException("User not present");
        }
        else if (employeeRepository.findEmployeeByUsername(username).isEmpty()) {
            Customer user = customerRepository.findCustomerByUsername(username)
                    .orElseThrow(() -> new UsernameNotFoundException("Customer not present"));
            return new org.springframework.security.core.userdetails.User(
                    user.getUsername(), user.getPassword(), getAuthorities(user.getCustomerRoles())
            );
        }
        else {
            Employee user = employeeRepository.findEmployeeByUsername(username)
                    .orElseThrow(() -> new UsernameNotFoundException("Employee not present"));
            return new org.springframework.security.core.userdetails.User(
                    user.getUsername(), user.getPassword(), getAuthorities(user.getEmployeeRoles())
            );
        }
    }
    public void createCustomer(UserDetails customer) {
        customerRepository.save((Customer) customer);
    }

    public void createEmployee(UserDetails employee) {
        employeeRepository.save((Employee) employee);
    }

    private Collection<? extends GrantedAuthority> getAuthorities(
            Collection<Role> roles) {

        return getGrantedAuthorities(getPrivileges(roles));
    }

    private List<String> getPrivileges(Collection<Role> roles) {

        List<String> privileges = new ArrayList<>();
        List<Privilege> collection = new ArrayList<>();
        for (Role role : roles) {
            privileges.add(role.getName());
            collection.addAll(role.getPrivileges());
        }
        for (Privilege item : collection) {
            privileges.add(item.getName());
        }
        return privileges;
    }

    private List<GrantedAuthority> getGrantedAuthorities(List<String> privileges) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String privilege : privileges) {
            authorities.add(new SimpleGrantedAuthority(privilege));
        }
        return authorities;
    }
}
