package event.event_microservice.repositories;

import event.event_microservice.models.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface SubscriptionRepository extends JpaRepository<Subscription, UUID> {
    @Query(value = "SELECT count(*) FROM SUBSCRIPTION WHERE ID_EVENT = ?", nativeQuery = true)
    Integer countEvents(UUID id);
}