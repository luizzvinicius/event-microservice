package event.event_microservice.services;

import event.event_microservice.dtos.EmailRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "${email-service-client.name}", url = "${email-service-client.url}")
public interface EmailServiceClient {

    @PostMapping
    void sendMail(@RequestBody EmailRequestDto body);
}