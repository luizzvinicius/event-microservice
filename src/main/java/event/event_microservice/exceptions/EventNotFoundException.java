package event.event_microservice.exceptions;

public class EventNotFoundException extends RuntimeException {
    public EventNotFoundException() {
        super("Event Not found");
    }
}
