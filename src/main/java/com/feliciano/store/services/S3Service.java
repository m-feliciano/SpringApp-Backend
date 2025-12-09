package com.feliciano.store.services;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.feliciano.store.services.exceptions.FileException;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

@Slf4j
@Service
public class S3Service {
    @Getter
    @Setter
    private ObjectMetadata meta;
    @Value("${s3.bucket}")
    private String bucketName;
    @Autowired
    private AmazonS3 amazonS3;

    public URI uploadFile(MultipartFile multipartFile) {
        try {
            String fileName = multipartFile.getOriginalFilename();
            InputStream is = multipartFile.getInputStream();
            String contentType = multipartFile.getContentType();
            return uploadFile(is, fileName, contentType);
        } catch (IOException e) {
            log.error("S3Service - IOException: " + e.getMessage());
            throw new RuntimeException("IO error");
        }
    }

    public URI uploadFile(InputStream is, String fileName, String contentType) {
        try {
            ObjectMetadata meta = new ObjectMetadata();
            meta.setContentType(contentType);
            log.info("Starting upload file {} to S3 bucket {}...", fileName, bucketName);

            amazonS3.putObject(bucketName, fileName, is, meta);
            log.info("Upload completed for file {}!", fileName);

            return amazonS3.getUrl(bucketName, fileName).toURI();
        } catch (URISyntaxException e) {
            log.error("S3Service - URISyntaxException: " + e.getMessage());
            throw new FileException("Error converting URL to URI");
        }
    }
}