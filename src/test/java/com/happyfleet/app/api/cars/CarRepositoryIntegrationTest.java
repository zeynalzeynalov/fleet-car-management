package com.happyfleet.app.api.cars;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
public class CarRepositoryIntegrationTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CarRepository carRepository;

    @Test
    public void whenFindByLicensePlate_thenReturnCar() {
        Car car = new Car();
        car.setBrand("brand0");
        car.setOperationCity("city0");
        car.setManufacturer("manufacturer0");
        car.setLicensePlate("Test_License_Plate0");
        entityManager.persistAndFlush(car);

        Optional<Car> carFound = carRepository.findByLicensePlate("Test_License_Plate0");
        assertThat(carFound.get().getLicensePlate()).isEqualTo(car.getLicensePlate());

        carRepository.deleteAll();
    }

    @Test
    public void whenInvalidName_thenReturnNull() {
        Optional<Car> carFound = carRepository.findByLicensePlate("INVALID_LICENSE_PLATE");
        assertThat(carFound).isEmpty();
    }

    @Test
    public void whenFindById_thenReturnCar() {
        Car car = new Car();
        car.setBrand("brand6");
        car.setManufacturer("manufacturer6");
        car.setOperationCity("city6");
        car.setLicensePlate("Test_License_Plate6");
        entityManager.persistAndFlush(car);

        Car carFound = carRepository.findById(car.getId()).orElse(null);
        assertThat(carFound.getBrand()).isEqualTo(car.getBrand());

        carRepository.deleteAll();

    }

    @Test
    public void whenCarIdNotFound_thenReturnNull() {
        Car car = carRepository.findById(0).orElse(null);
        assertThat(car).isNull();
    }

    @Test
    public void givenListOfCars_whenFindAll_thenReturnAllCars() {
        Car car1 = new Car();

        // TODO: Replace boiler plate code with list loop
        car1.setBrand("brand1");
        car1.setManufacturer("manufacturer1");
        car1.setOperationCity("city1");
        car1.setLicensePlate("Test_License_Plate1");

        Car car2 = new Car();

        car2.setBrand("brand2");
        car2.setManufacturer("manufacturer2");
        car2.setOperationCity("city2");
        car2.setLicensePlate("Test_License_Plate2");

        Car car3 = new Car();

        car3.setBrand("brand3");
        car3.setManufacturer("manufacturer3");
        car3.setOperationCity("city3");
        car3.setLicensePlate("Test_License_Plate3");

        entityManager.persist(car1);
        entityManager.persist(car2);
        entityManager.persist(car3);
        entityManager.flush();

        List<Car> cars = (List<Car>) carRepository.findAll();

        assertThat(cars).hasSize(3).extracting(Car::getBrand).containsOnly(car1.getBrand(), car2.getBrand(), car3.getBrand());

        carRepository.deleteAll();
    }

    @After
    public void truncateTable() {
        carRepository.deleteAll();
    }
}