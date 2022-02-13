package dev.jelemux.weatherstation.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter(AccessLevel.PROTECTED)
@MappedSuperclass
public abstract class AbstractMeasurementEntity {

    @Id
    private Long id;

    @Column(name = "time_of_measurement", nullable = false)
    private LocalDateTime timeOfMeasurement;
}
