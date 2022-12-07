package com.example.graceful.shutdown.config.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.event.ConsumerStoppingEvent;
import org.springframework.kafka.event.ContainerStoppedEvent;
import org.springframework.stereotype.Component;

@Component
@Slf4j
class KafkaContainerShutdownListener {

  @EventListener(ConsumerStoppingEvent.class)
  public void beforeShutdown() {
    log.warn("Stopping consumer container...");
  }

  @EventListener(ContainerStoppedEvent.class)
  public void afterShutdown() {
    log.warn("Consumer container stopped.");
  }
}
