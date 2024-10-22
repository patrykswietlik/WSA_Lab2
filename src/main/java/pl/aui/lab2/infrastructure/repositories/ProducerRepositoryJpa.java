package pl.aui.lab2.infrastructure.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.aui.lab2.infrastructure.ProducerEntity;

import java.util.Optional;
import java.util.UUID;

public interface ProducerRepositoryJpa extends JpaRepository<ProducerEntity, UUID> {
    Optional<ProducerEntity> findByName(String name);
}
