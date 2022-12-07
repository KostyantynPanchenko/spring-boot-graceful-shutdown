package com.example.graceful.shutdown.config.aws;

import com.amazonaws.services.sqs.AmazonSQSAsync;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.aws.messaging.config.SimpleMessageListenerContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableConfigurationProperties(SqsProperties.class)
@Configuration
class SqsConfig {

  @Bean
  public SimpleMessageListenerContainerFactory simpleMessageListenerContainerFactory(
      final AmazonSQSAsync amazonSQSAsync) {
    final var factory = new SimpleMessageListenerContainerFactory();
    factory.setAmazonSqs(amazonSQSAsync);
    factory.setWaitTimeOut(5);
    return factory;
  }
}
