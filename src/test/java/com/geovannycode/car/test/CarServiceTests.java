package com.geovannycode.car.test;

import com.geovannycode.car.test.domain.Car;
import com.geovannycode.car.test.domain.CarEntity;
import com.geovannycode.car.test.domain.CarMapper;
import com.geovannycode.car.test.domain.CarRepository;
import com.geovannycode.car.test.domain.CarService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class CarServiceTests {
    @Mock
    private CarRepository carRepository;

    @InjectMocks
    private CarService carService;

    private Car car;

    @BeforeEach
    public void setup(){
        car = new Car(
                "Toyota",
                "Corolla",
                "White",
                "ABC123",
                2021,
                BigDecimal.valueOf(20000.00)
        );
    }

    @DisplayName("JUnit test for saveCar method")
    @Test
    public void givenEmployeeObject_whenSaveCar_thenReturnCarObject(){
        // given - precondition or setup
        CarEntity carEntity = CarMapper.toCarEntity(car);
        when(carRepository.findByRegistrationNumber(car.registrationNumber()))
                .thenReturn(Optional.empty());

        when(carRepository.save(any(CarEntity.class))).thenReturn(carEntity);

        // when -  action or the behaviour that we are going test
        Car savedCar = carService.saveCar(car);

        // then - verify the output
        assertThat(savedCar).isNotNull();
        assertThat(savedCar.brand()).isEqualTo(car.brand());
        assertThat(savedCar.model()).isEqualTo(car.model());
        assertThat(savedCar.color()).isEqualTo(car.color());
        assertThat(savedCar.registrationNumber()).isEqualTo(car.registrationNumber());
        assertThat(savedCar.modelYear()).isEqualTo(car.modelYear());
        assertThat(savedCar.price()).isEqualTo(car.price());

        verify(carRepository, times(1)).findByRegistrationNumber(car.registrationNumber());
        verify(carRepository, times(1)).save(any(CarEntity.class));
    }
}
