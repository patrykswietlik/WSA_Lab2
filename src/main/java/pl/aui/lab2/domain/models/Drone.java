package pl.aui.lab2.domain.models;

import lombok.Builder;

import java.util.UUID;

@Builder
public record Drone(
        UUID id,
        String model,
        String serialNumber,
        Double speed,
        Double weight,
        Producer producer
) {
}
