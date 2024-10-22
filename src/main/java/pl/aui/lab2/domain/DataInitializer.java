package pl.aui.lab2.domain;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.aui.lab2.domain.services.DroneService;
import pl.aui.lab2.domain.services.ProducerService;
import pl.aui.lab2.infrastructure.DroneEntity;
import pl.aui.lab2.infrastructure.ProducerEntity;

import java.util.UUID;

@RequiredArgsConstructor
@Component
public class DataInitializer {

    private final DroneService droneService;
    private final ProducerService producerService;

    @PostConstruct
    public void init() {
        ProducerEntity producer1 = ProducerEntity.builder()
                .id(UUID.randomUUID())
                .name("DJI")
                .country("China")
                .build();

        ProducerEntity producer2 = ProducerEntity.builder()
                .id(UUID.randomUUID())
                .name("Parrot")
                .country("France")
                .build();

        producerService.create(producer1);
        producerService.create(producer2);

        DroneEntity drone1 = DroneEntity.builder()
                .id(UUID.randomUUID())
                .model("Phantom 4")
                .serialNumber("12345")
                .speed(50.0)
                .weight(1.5)
                .producer(producer1)
                .build();

        DroneEntity drone2 = DroneEntity.builder()
                .id(UUID.randomUUID())
                .model("Anafi")
                .serialNumber("67890")
                .speed(55.0)
                .weight(1.2)
                .producer(producer2)
                .build();

        droneService.create(drone1);
        droneService.create(drone2);
    }
}
