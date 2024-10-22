package pl.aui.lab2.domain.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.aui.lab2.infrastructure.DroneEntity;
import pl.aui.lab2.infrastructure.repositories.DroneRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class DroneService {

    private final DroneRepository droneRepository;

    public DroneEntity create(DroneEntity droneEntity) {
        return droneRepository.save(droneEntity);
    }

    public List<DroneEntity> getAll() {
        return droneRepository.getAll();
    }

    public Optional<DroneEntity> getById(UUID id) {
        return droneRepository.getById(id);
    }

    public List<DroneEntity> getAllByProducerName(String name) {
        return droneRepository.getByProducerName(name);
    }

    public void deleteById(UUID id) {
        droneRepository.deleteById(id);
    }
}