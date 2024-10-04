package event.event_microservice.exceptions;

public class EventFullException extends RuntimeException {
    public EventFullException() {
        super("Event Full!");
    }
}
