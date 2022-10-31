package com.happyfleet.app.api.cars;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CarService {
    private final CarRepository repository;

    public Car create(Car request) {
        return repository.save(request);
    }

    public Iterable<Car> getByLicensePlate(String licensePlate) {

        return repository.findByLicensePlate(licensePlate);
    }
}