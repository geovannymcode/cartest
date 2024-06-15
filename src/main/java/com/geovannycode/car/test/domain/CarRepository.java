package com.geovannycode.car.test.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CarRepository extends JpaRepository<CarEntity, UUID> {
    Optional<CarEntity> findByRegistrationNumber(String registrationNumber);
}
