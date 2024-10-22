package pl.aui.lab2.infrastructure.repositories;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.aui.lab2.infrastructure.DroneEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Repository
public class DroneRepository {

    private final DroneRepositoryJpa droneRepositoryJpa;

    public DroneEntity save(DroneEntity droneEntity) {
        return droneRepositoryJpa.save(droneEntity);
    }

    public List<DroneEntity> getAll() {
        return droneRepositoryJpa.findAll();
    }

    public List<DroneEntity> getByProducerName(String name) {
        return droneRepositoryJpa.findAllByProducerName(name);
    }

    public void deleteById(UUID id) {
        droneRepositoryJpa.deleteById(id);
    }

    public Optional<DroneEntity> getById(UUID id) {
        return droneRepositoryJpa.findById(id);
    }
}
