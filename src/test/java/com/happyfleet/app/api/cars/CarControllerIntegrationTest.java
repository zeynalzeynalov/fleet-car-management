package com.happyfleet.app.api.cars;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(value = CarController.class, excludeAutoConfiguration = SecurityAutoConfiguration.class)
public class CarControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CarService service;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void whenCreateCarWithPostCall_thenCreateCar() throws Exception {
        Car car = new Car();
        car.setBrand("AUDI");
        given(service.create(Mockito.any())).willReturn(car);

        mvc.perform(post("/api/cars").contentType(MediaType.APPLICATION_JSON).content(toJson(car)))
                .andExpect(status().isOk()).andExpect(jsonPath("$.data.brand", is("AUDI")));
        verify(service, VerificationModeFactory.times(1)).create(Mockito.any());
        reset(service);
    }

    @Test
    public void givenCarsList_whenGetAll_thenReturnJsonArray() throws Exception {
        Car car1 = new Car();
        Car car2 = new Car();
        Car car3 = new Car();
        car3.setBrand("brand3");

        List<Car> allCars = Arrays.asList(car1, car2, car3);

        given(service.getAll()).willReturn(allCars);

        mvc.perform(get("/api/cars").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data", hasSize(3)))
                .andExpect(jsonPath("$.data[0].brand", is(car1.getBrand())))
                .andExpect(jsonPath("$.data[1].brand", is(car2.getBrand())))
                .andExpect(jsonPath("$.data[2].brand", is(car3.getBrand())));
        verify(service, VerificationModeFactory.times(1)).getAll();
        reset(service);
    }

    static byte[] toJson(Object object) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return mapper.writeValueAsBytes(object);
    }
}