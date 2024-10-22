package pl.aui.lab2.domain.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.aui.lab2.infrastructure.ProducerEntity;
import pl.aui.lab2.infrastructure.repositories.ProducerRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProducerService {

    private final ProducerRepository producerRepository;

    public List<ProducerEntity> getAll() {
        return producerRepository.getAll();
    }

    public ProducerEntity getByName(String name) {
        return producerRepository.getByName(name);
    }

    public ProducerEntity create(ProducerEntity producerEntity) {
        return producerRepository.save(producerEntity);
    }
}
