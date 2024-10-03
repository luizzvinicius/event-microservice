package event.event_microservice.dtos;

import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record EmailRequestDto(
        @NotBlank @Email String from,
        @NotBlank @Email String to,
        @NotBlank @Size(max = 256) String subject,
        String content
) {}