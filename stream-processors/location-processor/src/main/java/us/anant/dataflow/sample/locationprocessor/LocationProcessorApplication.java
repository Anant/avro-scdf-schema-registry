package us.anant.dataflow.sample.locationprocessor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.schema.registry.client.EnableSchemaRegistryClient;

import org.springframework.messaging.converter.MessageConverter;
import org.springframework.cloud.schema.registry.avro.AvroSchemaMessageConverter;
import org.springframework.core.io.ClassPathResource;
import org.springframework.context.annotation.Bean;
import org.springframework.util.MimeType;

import java.io.IOException;
import org.springframework.cloud.stream.messaging.Processor; 

@SpringBootApplication
@EnableSchemaRegistryClient // https://www.baeldung.com/spring-cloud-stream-kafka-avro-confluent#5-enable-the-confluent-schema-registry-and-bindings
//@EnableBinding(Processor.class) 
public class LocationProcessorApplication {

	public static void main(String[] args) {
		SpringApplication.run(LocationProcessorApplication.class, args);
	}

  /**
   * couldn't get this working either, so leaving out, didn't 
   */
  /*
  @Bean
  public AvroSchemaMessageConverter locationMessageConverter() throws IOException {
		// default would be mimetype of just regular Avro (application/*+avro);
		AvroSchemaMessageConverter converter = new AvroSchemaMessageConverter(MimeType.valueOf("avro/bytes"));
		converter.setSchemaLocation(new ClassPathResource("avro/location.avsc"));


		return converter;
  }
  */
}
