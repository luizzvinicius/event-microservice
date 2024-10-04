package event.event_microservice.repositories;

import event.event_microservice.models.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EventRepository extends JpaRepository<Event, UUID> {
    @Query(value = "SELECT * FROM event e WHERE e.data > CURRENT_TIMESTAMP()", nativeQuery = true)
    Page<Event> findUpcomingEvents(Pageable p);
}