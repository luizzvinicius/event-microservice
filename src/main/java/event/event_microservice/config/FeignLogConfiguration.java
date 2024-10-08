package event.event_microservice.config;

import event.event_microservice.exceptions.FeignExceptions;
import feign.Logger;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignLogConfiguration {
    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.HEADERS;
    }

    @Bean
    ErrorDecoder feignErrorDecoder() {
        return new FeignExceptions();
    }
}