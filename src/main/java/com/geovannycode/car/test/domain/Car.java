package com.geovannycode.car.test.domain;

import java.math.BigDecimal;

public record Car(
        String brand,
        String model,
        String color,
        String registrationNumber,
        Integer modelYear,
        BigDecimal price
) { }
