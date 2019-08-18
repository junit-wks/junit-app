package com.capgemini.junit.wks.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.MongoClient;

@Configuration
@EnableMongoRepositories("com.capgemini.junit.wks.repository")
public class MongoConfig extends AbstractMongoConfiguration{

	private static final String DB_NAME = "junitDB";

	@Override
	public MongoClient mongoClient() {
		return new MongoClient();
	}

	@Override
	protected String getDatabaseName() {
		return DB_NAME;
	}

}
