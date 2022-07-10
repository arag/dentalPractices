package com.dh.beTFI.dentalPractices.repository;


import com.dh.beTFI.dentalPractices.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.Optional;

@Transactional
public interface IUserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}

