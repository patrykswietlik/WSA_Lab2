package pl.aui.lab2.infrastructure;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "drones")
@Entity
public class DroneEntity {

    @Id
    private UUID id;

    private String model;
    private String serialNumber;
    private Double speed;
    private Double weight;

    @ManyToOne
    @JoinColumn(name = "producer_id")
    private ProducerEntity producer;

    @Override
    public String toString() {
        return "DroneEntity [id=" + id + ", model=" + model + ", serialNumber=" + serialNumber + ", speed=" + speed + ", weight=" + weight + ", producer=" + producer.getName() + "]";
    }
}
