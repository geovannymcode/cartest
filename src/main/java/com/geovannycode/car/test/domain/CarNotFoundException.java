package com.geovannycode.car.test.domain;

public class CarNotFoundException extends RuntimeException {
    public CarNotFoundException(String message) {
        super(message);
    }

    public static CarNotFoundException forRegistrationNumber(String registrationNumber) {
        return new CarNotFoundException("Car with code " + registrationNumber + " not found");
    }
}