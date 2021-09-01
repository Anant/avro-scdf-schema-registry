# Deploy Spring Cloud Stream locally using Docker-compose
For reproducing error described here: https://stackoverflow.com/questions/68993255/spring-cloud-stream-is-generating-avro-schema-in-confluent-schema-registry-witho

Made following this example:
- https://github.com/spring-cloud/spring-cloud-dataflow-samples/tree/master/dataflow-website/stream-developer-guides/streams/standalone-stream-sample

Then combined with example from this blog to add Kafka Schema registry integration:
- https://www.baeldung.com/spring-cloud-stream-kafka-avro-confluent


# Instructions for Reproducing error
```
docker-compose up -d
```

### Build the jar
```
cd stream-processors/location-processor
./mvnw clean package
```

### Run Jar within Kafka (makes networking easy)
```
docker cp target/location-processor-0.0.1-SNAPSHOT.jar broker:/ && \
docker exec broker java -jar /location-processor-0.0.1-SNAPSHOT.jar
```

# Kafka helpers
### List topics
```
docker exec broker kafka-topics --list --bootstrap-server broker:9092
```

### produce to topic
```
docker exec -it schema-registry kafka-avro-console-producer \
--bootstrap-server broker:9092 \
--topic input \
--property value.schema='{"type":"record","namespace":"scdfpoc","name":"Location","fields":[{"name":"id","type":"string"},{"default":null,"name":"latitude","type":["null","string"]},{"default":null,"name":"longitude","type":["null","string"]}]}"]}' \
--property schema.registry.url=http://schema-registry:8081
```

Send some messages that fit our schema (NOTE seems like you need to send teh null values for some reason)
```
> {"id": "a", "latitude": null, "longitude": null}
> {"id": "b", "latitude": null, "longitude": null}
```

### Consume topic

```
docker exec -it schema-registry kafka-avro-console-consumer \
--bootstrap-server broker:9092 \
--topic output \
--property schema.registry.url=http://schema-registry:8081
```


If error is reproduced for you, you should see something like this:

```
"\u0002a\u0002\u0010fake-lat\u0002\u0012fake-long"
"\u0002a\u0002\u0010fake-lat\u0002\u0012fake-long"
```

