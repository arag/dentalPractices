package com.dh.beTFI.dentalPractices.exception.service.user;

/*
import com.dh.beTFI.dentalPractices.model.User;
import com.dh.beTFI.dentalPractices.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private IUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userFound = userRepository.findByUsername(username);

        if (userFound.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }

        return userFound.get();
    }
}
 */
