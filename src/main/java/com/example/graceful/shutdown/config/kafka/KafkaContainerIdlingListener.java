package com.example.graceful.shutdown.config.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.event.ListenerContainerIdleEvent;
import org.springframework.stereotype.Component;

@Component
@Slf4j
class KafkaContainerIdlingListener {

  @EventListener(ListenerContainerIdleEvent.class)
  public void onContainerIdling() {
    log.info("Container is idling...");
  }
}
