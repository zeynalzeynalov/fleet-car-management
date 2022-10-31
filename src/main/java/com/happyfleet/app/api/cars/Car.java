package com.happyfleet.app.api.cars;

import com.happyfleet.app.core.AuditableWithId;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(
        name = "car",
        uniqueConstraints = {@UniqueConstraint(columnNames = "licensePlate")})
public class Car extends AuditableWithId<String> {

    @Column(nullable = false, length = 20)
    private String licensePlate;

    // TODO: following properties can be normalized as new table: brand, manufacturer, operationCity, status
    @Column(nullable = false, length = 20)
    private String brand;

    @Column(nullable = false, length = 50)
    private String manufacturer;

    @Column(nullable = false, length = 20)
    private String operationCity;

    @Enumerated(EnumType.STRING)
    private CarStatus status;
}
