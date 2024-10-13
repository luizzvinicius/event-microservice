package event.event_microservice.fallbacks;

import org.springframework.stereotype.Component;
import event.event_microservice.dtos.EmailRequestDto;
import event.event_microservice.clients.EmailServiceClient;

@Component
public class EventFallBack implements EmailServiceClient {
    @Override
    public void sendMail(EmailRequestDto body) {
        throw new UnsupportedOperationException("paciencia q enviar email não tem retorno");
    }

    @Override
    public String getMessage() {
        return "Resposta padrão servidor";
    }
}