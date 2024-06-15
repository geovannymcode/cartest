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
}
