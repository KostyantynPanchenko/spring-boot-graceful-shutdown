package com.example.graceful.shutdown.config.aws;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.ErrorHandler;

@Component
@Slf4j
class SqsErrorHandler implements ErrorHandler {

  @Override
  public void handleError(final Throwable exception) {
    log.error("Failed to process the message.", exception);
  }
}
