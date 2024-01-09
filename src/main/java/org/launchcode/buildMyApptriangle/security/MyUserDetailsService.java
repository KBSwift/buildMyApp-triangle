package org.launchcode.buildMyApptriangle.security;

import org.launchcode.buildMyApptriangle.models.User;
import org.launchcode.buildMyApptriangle.models.data.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not present"));
                return user;
    }
    public void createUser(UserDetails user) {
        userRepository.save((User) user);
    }
}
