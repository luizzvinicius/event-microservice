package event.event_microservice.controllers;

import event.event_microservice.clients.EmailServiceClient;
import org.bouncycastle.asn1.ocsp.ResponderID;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.constraints.Max;
import event.event_microservice.models.Event;
import event.event_microservice.dtos.EventDto;
import org.springframework.http.ResponseEntity;
import jakarta.validation.constraints.PositiveOrZero;
import event.event_microservice.services.EventService;
import event.event_microservice.dtos.RegisterParticipantDto;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/event")
public class EventsController {
    private final EventService eventService;
    private final EmailServiceClient emailServiceClient;

    public EventsController(EventService eventService, EmailServiceClient emailServiceClient) {
        this.eventService = eventService;
        this.emailServiceClient = emailServiceClient;
    }

    @PostMapping
    public ResponseEntity<Event> createEvent(@RequestBody EventDto dto) {
        var event = eventService.createEvent(dto);
        return ResponseEntity.ok(event);
    }

    @PostMapping("/{eventId}/register")
    public ResponseEntity<Void> send(@PathVariable UUID eventId, @RequestBody RegisterParticipantDto dto) {
        eventService.registerParticipant(eventId, dto.emailParticipant());
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<Event>> getAllEvents(@RequestParam(defaultValue = "0") @PositiveOrZero int p,
                                                    @RequestParam(defaultValue = "8") @PositiveOrZero @Max(16) int size) {
       var events = eventService.getAllEvents(p, size);
       return ResponseEntity.ok(events);
    }

    @GetMapping("/upcoming")
    public ResponseEntity<List<Event>> getUpcomingEvents(@RequestParam(defaultValue = "0") @PositiveOrZero int p,
                                                         @RequestParam(defaultValue = "8") @PositiveOrZero @Max(16) int size) {
        var events = eventService.getUpcomingEvents(p, size);
        return ResponseEntity.ok(events);
    }

    @GetMapping("/teste")
    public ResponseEntity<String> teste() {
        var message = emailServiceClient.getMessage();
        return ResponseEntity.ok(message);
    }
}