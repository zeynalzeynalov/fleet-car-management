package com.happyfleet.app.api.cars;

import com.happyfleet.app.api.common.RestResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.Optional;

@RequestMapping("/api/cars")
@RestController
@RequiredArgsConstructor
public class CarController {

    private final CarService service;

    @GetMapping
    public ResponseEntity<RestResponse> getAll() {

        return ResponseEntity.ok(new RestResponse(service.getAll()));
    }

    @RequestMapping(value = "/{id}")
    public ResponseEntity<RestResponse> getCar(@PathVariable int id) {
        Optional<Car> car = service.getCar(id);
        if(car.isPresent()) {
            return ResponseEntity.ok(new RestResponse(car.get()));
        }
        else {
            return ResponseEntity.badRequest().body(new RestResponse("Car not found"));
        }
    }

    @RequestMapping(value = "/licenseplate/{licensePlateId}")
    public ResponseEntity<RestResponse> getByLicensePlate(@PathVariable String licensePlateId) {

        return ResponseEntity.ok(new RestResponse(service.getByLicensePlate(licensePlateId)));
    }

    // TODO: Implement additional CarCreateRequest.java data transfer class
    @PostMapping("")
    public ResponseEntity<RestResponse> create(@RequestBody Car carCreateRequest) {

        return ResponseEntity.ok(new RestResponse(service.create(carCreateRequest)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RestResponse> update(
            @RequestBody Car carUpdateRequest, @PathVariable int id) {

        return ResponseEntity.ok(new RestResponse("Not implemented :("));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<RestResponse> delete(@PathVariable int id) {

        return ResponseEntity.ok(new RestResponse("Not implemented :("));
    }
}
