package pl.aui.lab2.infrastructure;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "producers")
@Entity
public class ProducerEntity {

    @Id
    private UUID id;

    private String name;
    private String country;

    @OneToMany(mappedBy = "producer", fetch = FetchType.LAZY)
    private List<DroneEntity> drones;

    @Override
    public String toString() {
        return "ProducerEntity [id=" + id + ", name=" + name + ", country=" + country + "]";
    }
}
