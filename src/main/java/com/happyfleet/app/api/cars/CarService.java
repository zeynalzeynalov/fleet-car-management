package com.happyfleet.app.api.cars;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CarService {
    private final CarRepository repository;

    // TODO: Implement caching
    public Iterable<Car> getAll() {
        return repository.findAll();
    }

    public Optional<Car> getCar(int id) {
        return repository.findById(id);
    }

    public Optional<Car> getByLicensePlate(String licensePlate) {

        return repository.findByLicensePlate(licensePlate);
    }

    // TODO: Implement cache eviction
    public Car create(Car request) {
        return repository.save(request);
    }
}