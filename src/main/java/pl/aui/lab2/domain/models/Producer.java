package pl.aui.lab2.domain.models;

import lombok.Builder;

import java.util.List;
import java.util.UUID;

@Builder
public record Producer(
        UUID id,
        String name,
        String country,
        List<Drone> drones
) {
}
