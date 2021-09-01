package us.anant.dataflow.sample.locationprocessor;

import java.util.function.Function;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Value;
import org.apache.hc.client5.http.fluent.Request;
import org.apache.hc.client5.http.fluent.Content;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;

import scdfpoc.Location;

import org.apache.avro.generic.GenericRecord;
import org.apache.avro.generic.GenericData;
import org.apache.avro.Schema;
import org.apache.kafka.clients.producer.ProducerRecord;



@Configuration
public class LocationProcessor {


	private static final Logger logger = LoggerFactory.getLogger(LocationProcessor.class);

	/** 
	 * this uses the avro auto-generated pojo from the avsc file directly
	 * - tested, this builds and consumes well. Processor fails to send correctly parsed avro schema to schema registry however.
	 */

	@Bean
	public Function<Location, Location> process() {
	 	return locationRecord -> {
			try {
	 			locationRecord.setLatitude("fake-lat");
	 			locationRecord.setLongitude("fake-long");

				logger.info(locationRecord.toString());

			} catch (Exception e) {
				logger.error("Failed to process record");
				e.printStackTrace();
			} 

			return locationRecord;
		};
	}


	/**
	 * For using GenericRecord instead of a SpecificRecord
	 * - tested, and works just as well. 
	 * - should be able to generate a schema from its fields just as easily also.
	 */ 
	
	/*
	@Bean
	public Function<GenericRecord, GenericRecord> process() {
	 	return genericLocationRecord -> {
			try {
				logger.info(genericLocationRecord.toString());
				genericLocationRecord.put("latitude", "fake-lat");
				genericLocationRecord.put("longitude", "fake-long"); 


			} catch (Exception e) {
				logger.error("Failed to process record");
				e.printStackTrace();
			} 

			return genericLocationRecord;
		};
	}
	*/
}
