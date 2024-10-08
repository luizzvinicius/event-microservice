package event.event_microservice.exceptions;

import feign.Response;
import feign.codec.ErrorDecoder;

public class FeignExceptions implements ErrorDecoder {
    @Override
    public Exception decode(String s, Response response) {
        return switch (response.status()) {
            case 400 -> new RuntimeException("erro no cliente");
            case 500 -> new RuntimeException("erro do servidor");
            default -> new Exception("Exceção comum");
        };
    }
}