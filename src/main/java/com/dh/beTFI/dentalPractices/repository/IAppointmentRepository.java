package com.dh.beTFI.dentalPractices.repository;

import com.dh.beTFI.dentalPractices.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAppointmentRepository extends JpaRepository<Appointment, Long> {
}
