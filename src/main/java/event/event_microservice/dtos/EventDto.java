package event.event_microservice.dtos;

import java.time.LocalDateTime;

public record EventDto(
        String title,
        String description,
        int maxCapacity,
        LocalDateTime date)
{}