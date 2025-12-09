package com.feliciano.store.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.RegionUtils;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class S3Config {
    @Value("${aws.access_key_id:}")
    private String awsId;
    @Value("${aws.secret_access_key:}")
    private String awsKey;
    @Value("${s3.region:}")
    private String region;

    @Bean
    public AmazonS3 S3Client() {
        BasicAWSCredentials awsCredentials = new BasicAWSCredentials(awsId, awsKey);
        Region region = RegionUtils.getRegion(this.region);
        return AmazonS3ClientBuilder.standard()
                .withRegion(region.getName())
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .build();
    }
}
