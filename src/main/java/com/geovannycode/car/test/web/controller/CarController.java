package com.geovannycode.car.test.web.controller;

import com.geovannycode.car.test.domain.Car;
import com.geovannycode.car.test.domain.CarService;
import com.geovannycode.car.test.domain.CarNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/cars")
public class CarController {

    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping
    List<Car> getCars() {
        return carService.getAllCars();
    }

    @GetMapping("/{registrationNumber}")
    ResponseEntity<Car> getCarByRegistrationNumber(@PathVariable String registrationNumber) {
        return carService
                .getCarByRegistrationNumber(registrationNumber)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> CarNotFoundException.forRegistrationNumber(registrationNumber));
    }
}
