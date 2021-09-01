# Deploy Spring Cloud Data Flow locally using Docker-compose

```
docker-compose up -d
```

## Run using Spring Cloud Data Flow

### Build the jar
```
./mvnw clean package
```

### Run within Kafka (makes networking easy)
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

