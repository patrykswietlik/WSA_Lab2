package pl.aui.lab2.infrastructure.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.aui.lab2.infrastructure.DroneEntity;

import java.util.List;
import java.util.UUID;

public interface DroneRepositoryJpa extends JpaRepository<DroneEntity, UUID> {
    List<DroneEntity> findAllByProducerName(String name);
}
