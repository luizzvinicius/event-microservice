package event.event_microservice.clients;

import event.event_microservice.dtos.EmailRequestDto;
import event.event_microservice.fallbacks.EventFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name = "${email-service-client.name}",
        fallback = EventFallBack.class
)
public interface EmailServiceClient {

    @PostMapping("/api/email")
    void sendMail(@RequestBody EmailRequestDto body);

    @GetMapping
    String getMessage();
}