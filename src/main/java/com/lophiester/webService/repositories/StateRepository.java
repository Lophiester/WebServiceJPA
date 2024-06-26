package com.lophiester.webService.repositories;

import com.lophiester.webService.entities.State;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StateRepository extends JpaRepository<State, Long> {
}
