package com.example.graceful.shutdown.listener.sqs;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.aws.messaging.listener.SqsMessageDeletionPolicy;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
class CustomSqsListener {

  @SneakyThrows
  @SqsListener(value = "${custom.aws.sqs.queue-name}", deletionPolicy = SqsMessageDeletionPolicy.ON_SUCCESS)
  public void onNewSqsMessage(final String message) {
    log.info("We've got a new message: {}. Processing...", message);
    for (int i = 0; i < 5; i++) {
      for (int j = 0; j < 1_000_000_000; j++) {
        if (j % 500_000_000 == 0) {
          log.info("Processing...");
        }
      }
    }
    log.info("Done.");
  }
}
