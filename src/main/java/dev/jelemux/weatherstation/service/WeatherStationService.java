package dev.jelemux.weatherstation.service;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

@RequestScoped
public class WeatherStationService {

    @Inject
    private EntityManager entityManager;
}
