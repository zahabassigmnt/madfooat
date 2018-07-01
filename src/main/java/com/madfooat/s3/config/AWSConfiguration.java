package com.madfooat.s3.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

@Configuration
public class AWSConfiguration {

   


    @Bean
    public AmazonS3 amazonS3Client(@Value("${cloud.aws.credentials.accessKey}") String awsAccessKey,
			@Value("${cloud.aws.credentials.secretKey}") String awsSecretKey,
			@Value("${cloud.aws.region.static}") String awsRegion) {
    	
    	BasicAWSCredentials awsCreds = new BasicAWSCredentials(awsAccessKey, awsSecretKey);
    	AmazonS3 amazonS3 = AmazonS3ClientBuilder.standard().withRegion(awsRegion).withCredentials(new AWSStaticCredentialsProvider(awsCreds)).build();
    	
   
        return amazonS3;
    }
}