package com.example.shortletBackend.config;

import com.example.shortletBackend.entities.Users;
import com.example.shortletBackend.enums.Role;
import com.example.shortletBackend.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service @Slf4j
public class CustomDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Users> user = userRepository.findUsersByEmail(email);
        AuthenticatedUser userDetail = null;
        if (user.isPresent()) {
            userDetail = new AuthenticatedUser();
            userDetail.setUsers(user.get());
        } else {
            Users newUser= new Users(email, Role.USER);
            userRepository.save(newUser);
            userDetail.setUsers(newUser);

            log.info("added a new user");

        }
        return userDetail;
    }
    public UserDetails loadUserByUsername(String email,String name) throws UsernameNotFoundException {
        Optional<Users> user = userRepository.findUsersByEmail(email);
        AuthenticatedUser userDetail = null;
        if (user.isPresent()) {
            userDetail = new AuthenticatedUser();
            userDetail.setUsers(user.get());
        } else {
            Users newUser= new Users(name,email, Role.USER);
            userRepository.save(newUser);
            userDetail.setUsers(newUser);
            log.info("added a new user");

        }
        return userDetail;
    }
}
