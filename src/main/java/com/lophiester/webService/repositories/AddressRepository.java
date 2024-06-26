package com.lophiester.webService.repositories;

import com.lophiester.webService.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
