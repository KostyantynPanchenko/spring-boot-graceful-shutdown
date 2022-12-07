package com.example.graceful.shutdown.config.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.kafka.listener.KafkaListenerErrorHandler;
import org.springframework.kafka.listener.ListenerExecutionFailedException;
import org.springframework.messaging.Message;

@Configuration
@Slf4j
@RequiredArgsConstructor
class KafkaExceptionHandlingConfiguration {

  private final KafkaListenerEndpointRegistry registry;
  @Bean
  public KafkaListenerErrorHandler commonErrorHandler() {
    return (Message<?> message, ListenerExecutionFailedException exc) -> {
      if (isCustomException(exc)) { // ListenerExecutionFailedException
        log.warn(exc.getCause().getMessage(), exc.getCause());
      } else if (isFatalError(exc)) {
        log.error("Could not process the message. Kafka consumer will be stopped.");

        registry.stop();

        throw (RuntimeException) exc.getCause();
      } else {
        log.error("Could not process message: '{}'", message.getPayload(), exc);
      }
      return message;
    };
  }

    private static boolean isCustomException(final Exception exc) {
      return false; // TODO
    }

    private static boolean isFatalError(final Exception exc) {
      return false; // TODO
    }
}
