package pl.aui.lab2.infrastructure.repositories;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.aui.lab2.infrastructure.ProducerEntity;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class ProducerRepository {

    private final ProducerRepositoryJpa producerRepositoryJpa;

    public ProducerEntity save(ProducerEntity producerEntity) {
        return producerRepositoryJpa.save(producerEntity);
    }

    public List<ProducerEntity> getAll() {
        return producerRepositoryJpa.findAll();
    }

    public ProducerEntity getByName(String name) {
        return producerRepositoryJpa.findByName(name).orElse(null);
    }
}
