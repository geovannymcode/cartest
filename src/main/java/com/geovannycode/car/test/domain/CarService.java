package com.geovannycode.car.test.domain;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
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
        checkIfCarExists(car.registrationNumber());
        CarEntity carEntity = CarMapper.toCarEntity(car);
        CarEntity savedCarEntity = carRepository.save(carEntity);
        return CarMapper.toCar(savedCarEntity);
    }

    public Car updateCar(String registrationNumber, Car car) {
        CarEntity carEntityToUpdate = carRepository.findByRegistrationNumber(registrationNumber)
                .orElseThrow(() -> new CarNotFoundException("Car not found with given registration number:" + registrationNumber));
        CarMapper.updateCarEntityFromCar(carEntityToUpdate, car);
        CarEntity updatedCarEntity = carRepository.save(carEntityToUpdate);
        return CarMapper.toCar(updatedCarEntity);
    }

    public void deleteCar(UUID id) {
       if (!carRepository.existsById(id)) {
           throw new CarNotFoundException("Car not found with given id:" + id);
       }
        carRepository.deleteById(id);
    }

    private void checkIfCarExists(String registrationNumber) {
        Optional<CarEntity> existingCar = carRepository.findByRegistrationNumber(registrationNumber);
        if(existingCar.isPresent()){
            throw new CarNotFoundException("Car already exist with given registration number:" + registrationNumber);
        }
    }
}
