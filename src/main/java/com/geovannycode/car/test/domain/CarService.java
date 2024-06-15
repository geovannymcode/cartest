package com.geovannycode.car.test.domain;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CarService {

    private final CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public List<Car> getAllCars() {
        return carRepository.findAll().stream().map(CarMapper::toCar).collect(Collectors.toList());
    }

    public Optional<Car> getCarByRegistrationNumber(String registrationNumber) {
        return carRepository.findByRegistrationNumber(registrationNumber).map(CarMapper::toCar);
    }

    public Car saveCar(Car car) {
        CarEntity carEntity = CarMapper.toCarEntity(car);
        Optional<CarEntity> savedCar = carRepository.findByRegistrationNumber(carEntity.getRegistrationNumber());
        if(savedCar.isPresent()){
            throw new CarNotFoundException("Car already exist with given email:" + carEntity.getRegistrationNumber());
        }
        CarEntity savedCarEntity = carRepository.save(carEntity);
        return CarMapper.toCar(savedCarEntity);
    }

}
