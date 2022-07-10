package com.dh.beTFI.dentalPractices.securityConfig;

import com.dh.beTFI.dentalPractices.model.User;
import com.dh.beTFI.dentalPractices.model.enums.UserRoleEnum;
import com.dh.beTFI.dentalPractices.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {
    @Autowired
    private IUserRepository userRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        String passwordUser = passwordEncoder.encode("araUser");
        User user = new User("ara", "araceli", "ara@gmail.com", passwordUser, UserRoleEnum.ROLE_USER);

        String passwordAdmin = passwordEncoder.encode("araAdmin");
        User admin = new User("admin", "admin", "admin@admin.com", passwordAdmin, UserRoleEnum.ROLE_ADMIN);

        userRepository.save(user);
        userRepository.save(admin);
    }
}
