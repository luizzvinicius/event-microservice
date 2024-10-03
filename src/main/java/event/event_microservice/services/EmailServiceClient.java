package event.event_microservice.services;

import event.event_microservice.dtos.EmailRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "email-service", url = "http://localhost:8081/api/email")
public interface EmailServiceClient {

    @PostMapping
    void sendMail(@RequestBody EmailRequestDto body);
}