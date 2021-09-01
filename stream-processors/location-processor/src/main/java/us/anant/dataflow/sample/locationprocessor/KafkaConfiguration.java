package us.anant.dataflow.sample.locationprocessor;

import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.listener.LoggingErrorHandler;
import org.springframework.context.annotation.Bean;

/**
 * - Taken from blog post: https://www.confluent.io/blog/spring-kafka-can-your-kafka-consumers-handle-a-poison-pill/#consequences
 */ 
@Configuration
@EnableKafka
public class KafkaConfiguration {

  /**
   * Boot will autowire this into the container factory.
   */
  @Bean
  public LoggingErrorHandler errorHandler() {
    return new LoggingErrorHandler();
  }
}
