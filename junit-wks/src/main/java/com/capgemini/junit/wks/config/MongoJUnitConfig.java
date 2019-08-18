package com.capgemini.junit.wks.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories("com.capgemini.junit.wks.repository")
public class MongoJUnitConfig extends MongoConfig{

	private static final String DB_NAME = "junitTestDB";

	@Override
	protected String getDatabaseName() {
		return DB_NAME;
	}

}
