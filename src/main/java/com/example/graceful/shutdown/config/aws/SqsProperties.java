package com.example.graceful.shutdown.config.aws;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@Getter
@RequiredArgsConstructor
@ConfigurationProperties(prefix = "custom.aws.sqs")
@ConstructorBinding
class SqsProperties {

  private final String accessKey;
  private final String secretKey;
}
