package com.tcc.lojavirtual.domain.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;


// CONFIGURAÇÃO DO S3 (ACESSO) -> @Bean Inject
// As definições são definidas em application.properties
// Código padrão API Amazon
@Configuration
public class S3Config {

	@Value("${aws.access_key_id}")
	private String awsId;
	@Value("${aws.secret_access_key}")
	private String awsKey;
	@Value("${s3.region}")
	private String region;
	
	@Bean
	public AmazonS3 s3cliente() {
		BasicAWSCredentials awsCred = new BasicAWSCredentials(awsId,awsKey);
		AmazonS3 s3cliente = AmazonS3ClientBuilder.standard().withRegion(Regions.fromName(region))
				.withCredentials(new AWSStaticCredentialsProvider(awsCred)).build();
		
		return s3cliente;
	}
}
