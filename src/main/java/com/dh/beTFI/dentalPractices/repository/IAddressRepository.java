package com.dh.beTFI.dentalPractices.repository;

import com.dh.beTFI.dentalPractices.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAddressRepository extends JpaRepository<Address, Long> {
}
