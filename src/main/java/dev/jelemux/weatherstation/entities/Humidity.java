package dev.jelemux.weatherstation.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter(AccessLevel.PROTECTED)
@Entity
@Table(name = "humidity")
public class Humidity extends AbstractMeasurementEntity {

    @Column(name = "relative_humidity", nullable = false)
    private Float relativeHumidity;
}
