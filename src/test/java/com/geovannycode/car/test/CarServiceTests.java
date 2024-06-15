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
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.doNothing;
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
    public void givenCarObject_whenSaveCar_thenReturnCarObject(){
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

    @DisplayName("JUnit test for getCarByRegistrationNumber method")
    @Test
    public void givenCarRegistrationNumber_whenGetCarByRegistrationNumber_thenReturnCarObject(){
        CarEntity carEntity = CarMapper.toCarEntity(car);
        // given
        when(carRepository.findByRegistrationNumber("ABC123")).thenReturn(Optional.of(carEntity));

        // when
        Car savedCar = carService.getCarByRegistrationNumber(car.registrationNumber()).get();

        // then
        assertThat(savedCar).isNotNull();

    }

    @DisplayName("JUnit test for getAllCars method")
    @Test
    public void givenCarsList_whenGetAllCars_thenReturnCarsList(){
        // given - precondition or setup
        Car car1 = new Car(
                "Chevrolet",
                "Aveo",
                "Green",
                "KEA144",
                2022,
                BigDecimal.valueOf(30000.00)
        );
        CarEntity carEntity = CarMapper.toCarEntity(car);
        CarEntity carEntity1 = CarMapper.toCarEntity(car1);

        when(carRepository.findAll()).thenReturn(List.of(carEntity,carEntity1));

        // when -  action or the behaviour that we are going test
        List<Car> carList = carService.getAllCars();

        // then - verify the output
        assertThat(carList).isNotNull();
        assertThat(carList.size()).isEqualTo(2);
    }

    @DisplayName("JUnit test for updateCar method")
    @Test
    public void givenCarObject_whenUpdateCar_thenReturnUpdatedCar(){
        // given - precondition or setup
        String registrationNumber = "ABC123";
        CarEntity carEntity = CarMapper.toCarEntity(car);
        when(carRepository.findByRegistrationNumber(registrationNumber)).thenReturn(Optional.of(carEntity));
        when(carRepository.save(any(CarEntity.class))).thenReturn(carEntity);

        Car updatedCarInput = new Car(
                car.brand(),
                car.model(),
                "Red",
                car.registrationNumber(),
                car.modelYear(),
                BigDecimal.valueOf(25000.00) // updated price
        );

        // when -  action or the behaviour that we are going test
        Car updatedCar = carService.updateCar(registrationNumber, updatedCarInput);

        // then - verify the output
        assertThat(updatedCar.color()).isEqualTo("Red");
        assertThat(updatedCar.price()).isEqualTo(BigDecimal.valueOf(25000.00));
    }

    @DisplayName("JUnit test for deleteCar method")
    @Test
    public void givenCarRegistrationNumber_whenDeleteCar_thenNothing(){
       // given - precondition or setup
       UUID carId = UUID.fromString("b73d2343-f072-490a-b796-cbfa6de20f67");
       CarEntity carEntity = CarMapper.toCarEntity(car);
       carEntity.setId(carId);
       when(carRepository.findById(carEntity.getId())).thenReturn(Optional.of(carEntity));

       doNothing().when(carRepository).deleteById(carId);

       // when -  action or the behaviour that we are going test
       carService.deleteCar(carId);

       // then - verify the output
       verify(carRepository, times(1)).deleteById(carId);
    }


}
