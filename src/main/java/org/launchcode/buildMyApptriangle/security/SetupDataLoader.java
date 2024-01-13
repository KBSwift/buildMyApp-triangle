package org.launchcode.buildMyApptriangle.security;

import org.launchcode.buildMyApptriangle.models.Privilege;
import org.launchcode.buildMyApptriangle.models.Role;
import org.launchcode.buildMyApptriangle.models.User;
import org.launchcode.buildMyApptriangle.models.data.PrivilegeRepository;
import org.launchcode.buildMyApptriangle.models.data.RoleRepository;
import org.launchcode.buildMyApptriangle.models.data.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

//To be run *once*

//@Component
//public class SetupDataLoader implements
//        ApplicationListener<ContextRefreshedEvent> {
//
//    boolean alreadySetup = false;
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private RoleRepository roleRepository;
//
//    @Autowired
//    private PrivilegeRepository privilegeRepository;
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    @Override
//    @Transactional
//    public void onApplicationEvent(ContextRefreshedEvent event) {
//
//        if (alreadySetup)
//            return;
//
//        Privilege readPrivilege
//                = createPrivilegeIfNotFound("READ_PRIVILEGE");
//        Privilege writePrivilege
//                = createPrivilegeIfNotFound("WRITE_PRIVILEGE");
//
//        createRoleIfNotFound("ROLE_ADMIN", Arrays.asList(readPrivilege, writePrivilege));
//        createRoleIfNotFound("ROLE_EMPLOYEE", Arrays.asList(readPrivilege));
//        createRoleIfNotFound("ROLE_CUSTOMER", Arrays.asList(readPrivilege));
//
//        // Create default Admin
//        Role defaultAdminUser = roleRepository.findByName("ROLE_ADMIN");
//        User adminUser = new User();
//        adminUser.setUsername("BrushUp@gmail.com");
//        adminUser.setFirstName("John");
//        adminUser.setLastName("Brush");
//        adminUser.setPassword(passwordEncoder.encode("12345"));
//        adminUser.setRoles(Arrays.asList(defaultAdminUser));
//        userRepository.save(adminUser);
//
//        // Create default Employee
//        Role defaultEmployeeUser = roleRepository.findByName("ROLE_EMPLOYEE");
//        User EmployeeUser = new User();
//        EmployeeUser.setUsername("Paintgood@gmail.com");
//        EmployeeUser.setFirstName("Jane");
//        EmployeeUser.setLastName("Paintgood");
//        EmployeeUser.setPassword(passwordEncoder.encode("12345"));
//        EmployeeUser.setRoles(Arrays.asList(defaultEmployeeUser));
//        userRepository.save(EmployeeUser);
//        alreadySetup = true;
//
//        // Create default Customer
//        Role defaultCustomerUser = roleRepository.findByName("ROLE_CUSTOMER");
//        User customerUser = new User();
//        customerUser.setUsername("Cantpaint@gmail.com");
//        customerUser.setFirstName("Bob");
//        customerUser.setLastName("Cantpaint");
//        customerUser.setPassword(passwordEncoder.encode("12345"));
//        customerUser.setRoles(Arrays.asList(defaultCustomerUser));
//        userRepository.save(customerUser);
//        alreadySetup = true;
//    }
//
//    @Transactional
//    Privilege createPrivilegeIfNotFound(String name) {
//
//        Privilege privilege = privilegeRepository.findByName(name);
//        if (privilege == null) {
//            privilege = new Privilege(name);
//            privilegeRepository.save(privilege);
//        }
//        return privilege;
//    }
//
//    @Transactional
//    Role createRoleIfNotFound(
//            String name, Collection<Privilege> privileges) {
//
//        Role role = roleRepository.findByName(name);
//        if (role == null) {
//            role = new Role(name);
//            role.setPrivileges(privileges);
//            roleRepository.save(role);
//        }
//        return role;
//    }
//}