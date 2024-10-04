package event.event_microservice.models;

import event.event_microservice.dtos.EventDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Event implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToMany(mappedBy = "id", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Subscription> subscriptions;

    private String title;

    private String description;

    private int maxCapacity;

    private LocalDateTime data;

    public Event(EventDto dto) {
        this.title = dto.title();
        this.description = dto.description();
        this.maxCapacity = dto.maxCapacity();
        this.data = dto.date();
    }
}