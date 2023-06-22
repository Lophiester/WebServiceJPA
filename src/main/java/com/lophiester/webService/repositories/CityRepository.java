package com.lophiester.webService.repositories;

import com.lophiester.webService.entities.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, Long> {
}
