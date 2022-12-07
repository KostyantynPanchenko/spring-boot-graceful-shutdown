# AWS SQS listener and Apache Kafka listener graceful shutdown example.

Spring Boot registers a shutdown hook and in case of a web application stops serving new requests and gives an opportunity for ongoing requests to finish.

`curl 'http://localhost:8083/actuator/shutdown' -i -X POST` to shut down the application from the terminal

### SQS listener nuances

The default timeout to shut down the SQS listener is equal to 10 seconds as defined in `SimpleMessageListenerContainer#queueStopTimeout`.

To prevent `java.util.concurrent.TimeoutException: null
at java.base/java.util.concurrent.FutureTask.get(FutureTask.java:204) ~[na:na]
at org.springframework.cloud.aws.messaging.listener.SimpleMessageListenerContainer.waitForRunningQueuesToStop(SimpleMessageListenerContainer.java:162) ~[spring-cloud-aws-messaging-2.2.6.RELEASE.jar:2.2.6.RELEASE]`
you have to either configure (increase) the `queueStopTimeout` to be greater than 20
or decrease the poll `waitTimeOut` to be less than 10:

```java
  @Bean
  public SimpleMessageListenerContainerFactory simpleMessageListenerContainerFactory(final AmazonSQSAsync amazonSQSAsync) {
    final var factory = new SimpleMessageListenerContainerFactory();
    factory.setAmazonSqs(amazonSQSAsync);
    factory.setWaitTimeOut(5);
    return factory;
  }
```

### Kafka
As per Garry Russel from Spring Team:
`kill -9` is like the death star

     Some of the more commonly used signals:

     1       HUP (hang up)
     2       INT (interrupt)
     3       QUIT (quit)
     6       ABRT (abort)
     9       KILL (non-catchable, non-ignorable kill)
     14      ALRM (alarm clock)
     15      TERM (software termination signal)
The default kill signal SIGTERM (15)

`kill <pid>`

Boot will shut down everything gracefully; it registers a shutdown hook, which can't intercept a `kill -9`.


### Useful links
* [Spring Cloud AWS SQS fails to connect to service endpoint locally](https://stackoverflow.com/questions/59517989/spring-cloud-aws-sqs-fails-to-connect-to-service-endpoint-locally)
* [Prevent spring-cloud-aws-messaging from trying to stop the queue](https://stackoverflow.com/questions/58601625/prevent-spring-cloud-aws-messaging-from-trying-to-stop-the-queue)
* [Spring Boot AWS SQS Listener Example](https://github.com/netsurfingzone/spring-boot-aws-sqs-listener-example)
* [How to gracefully shutdown spring-kafka consumer application](https://stackoverflow.com/questions/61653716/how-to-gracefully-shutdown-spring-kafka-consumer-application)
