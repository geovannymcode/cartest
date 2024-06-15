package com.geovannycode.car.test.domain;

import org.springframework.stereotype.Component;

@Component
public class CarMapper {
    public static Car toCar(CarEntity car) {
        return new Car(
                car.getBrand(),
                car.getModel(),
                car.getColor(),
                car.getRegistrationNumber(),
                car.getModelYear(),
                car.getPrice()
        );
    }

    public static CarEntity toCarEntity(Car car) {
        return new CarEntity(
                car.brand(),
                car.model(),
                car.color(),
                car.registrationNumber(),
                car.modelYear(),
                car.price()
        );
    }

    public static void updateCarEntityFromCar(CarEntity carEntity, Car car) {
        carEntity.setBrand(car.brand());
        carEntity.setModel(car.model());
        carEntity.setColor(car.color());
        carEntity.setRegistrationNumber(car.registrationNumber());
        carEntity.setModelYear(car.modelYear());
        carEntity.setPrice(car.price());
    }
}
