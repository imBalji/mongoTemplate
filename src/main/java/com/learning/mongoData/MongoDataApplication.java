package com.learning.mongoData;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;

import com.learning.mongoData.db.AircraftDbReadConverter;
import com.learning.mongoData.db.AircraftDbWriteConverter;

@SpringBootApplication
public class MongoDataApplication {

	public static void main(String[] args) {
		SpringApplication.run(MongoDataApplication.class, args);
	}
	
	@Bean
	public MongoCustomConversions customConversions() {
		return new MongoCustomConversions(List.of(new AircraftDbReadConverter(), new AircraftDbWriteConverter()));
	}
}