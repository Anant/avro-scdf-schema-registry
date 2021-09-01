package us.anant.dataflow.sample.locationprocessor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.schema.client.ConfluentSchemaRegistryClient;
import org.springframework.cloud.stream.schema.client.SchemaRegistryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * - Docs: https://docs.spring.io/spring-cloud-schema-registry/docs/1.1.3-SNAPSHOT/reference/html/spring-cloud-schema-registry.html#_using_confluents_schema_registry
 * - Example: https://github.com/eugenp/tutorials/blob/master/spring-cloud/spring-cloud-stream/spring-cloud-stream-kafka/src/main/java/com/baeldung/config/SchemRegistryConfig.java
 */ 
@Configuration
public class SchemaRegistryConfig {

  @Bean
  public SchemaRegistryClient schemaRegistryClient(@Value("${spring.cloud.stream.kafka.binder.producer-properties.schema.registry.url}") String endPoint) {
    ConfluentSchemaRegistryClient client = new ConfluentSchemaRegistryClient();
    client.setEndpoint(endPoint);

    return client;
  }
}
