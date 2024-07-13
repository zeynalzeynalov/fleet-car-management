package com.happyfleet.app.api.cars;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CarRepository extends CrudRepository<Car, Integer> {
    Optional<Car> findByLicensePlate(String licensePlate);
}