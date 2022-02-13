package dev.jelemux.weatherstation.resource;

import dev.jelemux.weatherstation.service.WeatherStationService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("weatherStation")
@RequestScoped
@Produces(MediaType.APPLICATION_JSON)
public class WeatherStationResource {

    @Inject
    WeatherStationService service;
}
