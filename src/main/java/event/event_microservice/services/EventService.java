package event.event_microservice.services;

import event.event_microservice.exceptions.EventFullException;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import event.event_microservice.dtos.EventDto;
import event.event_microservice.dtos.EmailRequestDto;
import event.event_microservice.models.Event;
import event.event_microservice.models.Subscription;
import event.event_microservice.repositories.EventRepository;
import org.springframework.data.domain.PageRequest;
import event.event_microservice.exceptions.EventNotFoundException;
import event.event_microservice.repositories.SubscriptionRepository;

import java.util.UUID;
import java.util.List;

@Log4j2
@Service
public class EventService {
    private final EmailServiceClient emailServiceClient;
    private final EventRepository eventRepository;
    private final SubscriptionRepository subscriptionRepository;

    public EventService(EmailServiceClient emailServiceClient, EventRepository eventRepository, SubscriptionRepository subscriptionRepository) {
        this.emailServiceClient = emailServiceClient;
        this.eventRepository = eventRepository;
        this.subscriptionRepository = subscriptionRepository;
    }

    public Event createEvent(EventDto eventDto) {
        return eventRepository.save(new Event(eventDto));
    }

    public void registerParticipant(UUID eventId, String participantEmail) {
        var event = eventRepository.findById(eventId).orElseThrow(EventNotFoundException::new);

        if (subscriptionRepository.countEvents(eventId) >= event.getMaxCapacity()) {
            throw new EventFullException();
        }

        subscriptionRepository.save(new Subscription(event, participantEmail));
        emailServiceClient.sendMail(new EmailRequestDto(
                "viniciusadr244@gmail.com",
                "marcelo.mldsl49@gmail.com",
                String.format("Your subscription in %s", event.getTitle()),
                "Você tá inscrito"
        ));
    }

    public List<Event> getAllEvents(int page, int size) {
        return eventRepository.findAll(PageRequest.of(page, size)).getContent();
    }

    public List<Event> getUpcomingEvents(int p, int size) {
        return eventRepository.findUpcomingEvents(PageRequest.of(p, size)).getContent();
    }
}