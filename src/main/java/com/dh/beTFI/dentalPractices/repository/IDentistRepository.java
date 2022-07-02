package com.dh.beTFI.dentalPractices.repository;

import com.dh.beTFI.dentalPractices.model.Dentist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDentistRepository extends JpaRepository<Dentist, Long> {
}
