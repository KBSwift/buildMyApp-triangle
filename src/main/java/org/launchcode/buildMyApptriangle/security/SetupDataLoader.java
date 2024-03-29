package org.launchcode.buildMyApptriangle.security;

//To be run *once*

import jakarta.transaction.Transactional;
import org.launchcode.buildMyApptriangle.models.Customer;
import org.launchcode.buildMyApptriangle.models.Employee;
import org.launchcode.buildMyApptriangle.models.Privilege;
import org.launchcode.buildMyApptriangle.models.Role;
import org.launchcode.buildMyApptriangle.models.data.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collection;

@Component
public class SetupDataLoader implements
        ApplicationListener<ContextRefreshedEvent> {

    boolean alreadySetup = false;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private ContractRepository contractRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PrivilegeRepository privilegeRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {

        if (alreadySetup)
            return;

        Privilege readPrivilege
                = createPrivilegeIfNotFound("READ_PRIVILEGE");
        Privilege writePrivilege
                = createPrivilegeIfNotFound("WRITE_PRIVILEGE");

        createRoleIfNotFound("ROLE_ADMIN", Arrays.asList(readPrivilege, writePrivilege));
        createRoleIfNotFound("ROLE_EMPLOYEE", Arrays.asList(readPrivilege));
        createRoleIfNotFound("ROLE_CUSTOMER", Arrays.asList(readPrivilege));

        // Create default Admin
        Role defaultAdminUser = roleRepository.findByName("ROLE_ADMIN");
        Employee adminEmployee = new Employee();
        adminEmployee.setUsername("BrushUp@gmail.com");
        adminEmployee.setFirstName("John");
        adminEmployee.setLastName("Brush");
        adminEmployee.setPassword(passwordEncoder.encode("12345"));
        adminEmployee.setEmployeeRoles(Arrays.asList(defaultAdminUser));
        employeeRepository.save(adminEmployee);

        // Create default Employee
        Role defaultEmployeeUser = roleRepository.findByName("ROLE_EMPLOYEE");
        Employee employeeUser = new Employee();
        employeeUser.setUsername("Paintgood@gmail.com");
        employeeUser.setFirstName("Jane");
        employeeUser.setLastName("Paintgood");
        employeeUser.setPassword(passwordEncoder.encode("12345"));
        employeeUser.setEmployeeRoles(Arrays.asList(defaultEmployeeUser));
        employeeRepository.save(employeeUser);

        // Create default Customer
        Role defaultCustomerUser = roleRepository.findByName("ROLE_CUSTOMER");
        Customer customerUser = new Customer();
        customerUser.setUsername("Cantpaint@gmail.com");
        customerUser.setFirstName("Bob");
        customerUser.setLastName("Cantpaint");
        customerUser.setPassword(passwordEncoder.encode("12345"));
        customerUser.setCustomerRoles(Arrays.asList(defaultCustomerUser));
        customerRepository.save(customerUser);
    }

    @Transactional
    Privilege createPrivilegeIfNotFound(String name) {

        Privilege privilege = privilegeRepository.findByName(name);
        if (privilege == null) {
            privilege = new Privilege(name);
            privilegeRepository.save(privilege);
        }
        return privilege;
    }

    @Transactional
    Role createRoleIfNotFound(
            String name, Collection<Privilege> privileges) {
        Role role = roleRepository.findByName(name);
        if (role == null) {
            role = new Role(name);
            role.setPrivileges(privileges);
            roleRepository.save(role);
        }
        return role;
    }
}