package dev.jelemux.weatherstation;

import java.util.logging.Logger;

import org.eclipse.microprofile.config.ConfigProvider;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import dev.jelemux.weatherstation.resource.WeatherStationResource;
import io.swagger.v3.jaxrs2.integration.resources.AcceptHeaderOpenApiResource;
import io.swagger.v3.jaxrs2.integration.resources.OpenApiResource;
import jakarta.ws.rs.core.UriBuilder;

public final class WeatherStationServer {

    private static final int AUTO_SELECT_PORT = 0;

    public static void main(final String[] args) throws InterruptedException {
        final var config = ConfigProvider.getConfig();
        final var host = config
                .getOptionalValue("http.host", String.class)
                .orElse("localhost");
        final var port = config
                .getOptionalValue("http.port", Integer.class)
                .orElse(AUTO_SELECT_PORT);

        final var baseUri = UriBuilder.fromUri("")
                .scheme("http")
                .host(host)
                .port(port)
                .path("/")
                .build();
        final var resourceConfigFromClasses = new ResourceConfig(
            WeatherStationResource.class, 
            OpenApiResource.class, 
            AcceptHeaderOpenApiResource.class
        ).packages("dev.jelemux.weatherstation");

        final var httpServer = GrizzlyHttpServerFactory.createHttpServer(baseUri, resourceConfigFromClasses);
        final int actualPort = httpServer.getListener("grizzly").getPort();

        rootLogger().info(() -> String.format("Server running on port %d - Send SIGKILL to shutdown.%n", actualPort));

        Thread.currentThread().join();
    }

    private static final Logger rootLogger() {
        var logger = Logger.getGlobal();
        while (logger.getParent() != null)
            logger = logger.getParent();
        return logger;
    }
}
