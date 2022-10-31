package com.happyfleet.app.api.cars;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CarRepository extends CrudRepository<Car, Integer> {
    List<Car> findByLicensePlate(String licensePlate);
}