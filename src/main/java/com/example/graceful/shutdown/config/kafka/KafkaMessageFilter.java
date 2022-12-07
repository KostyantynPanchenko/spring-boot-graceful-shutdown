package com.example.graceful.shutdown.config.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.kafka.listener.adapter.RecordFilterStrategy;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
class KafkaMessageFilter implements RecordFilterStrategy<Object, Object> {

  private final KafkaListenerEndpointRegistry registry;

  @Override
  public boolean filter(final ConsumerRecord<Object, Object> consumerRecord) {
    final var messageKey = consumerRecord.key();
    if (!(messageKey instanceof String)) {
      log.error("Invalid message key type");
      return true;
    }
    if ("Barkley".equals(messageKey)) {
      log.error("Barkley!");

      registry.stop();

      return true;
    }
    return false;
  }
}
