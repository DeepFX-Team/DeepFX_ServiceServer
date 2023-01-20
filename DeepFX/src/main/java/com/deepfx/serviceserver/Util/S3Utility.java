package com.deepfx.serviceserver.Util;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.deepfx.serviceserver.Base.BaseException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Component
public class S3Utility {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    private final AmazonS3Client amazonS3Client;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    public String upload2S3(MultipartFile multipartFile, String location) throws IOException {
        File targetFile = convertFile2Local(multipartFile)
                .orElseThrow(() -> new IllegalArgumentException("MultipartFile -> File convert fail"));

        return uploadFile(targetFile, location);
    }

    private String uploadFile(File targetFile, String location) {
        String fileName = location + "/" + UUID.randomUUID() + targetFile.getName();
        String s3FileUrl = postS3(targetFile, fileName);
        deleteLocalFile(targetFile);

        return s3FileUrl;
    }

    private Optional<File> convertFile2Local(MultipartFile file) throws IOException{
        File localFile = new File(file.getOriginalFilename());

        if(localFile.createNewFile()) {
            try(FileOutputStream fos = new FileOutputStream(localFile)) {
                fos.write(file.getBytes());
            }
            //파일이 있다면 파일을 리턴
            return Optional.of(localFile);
        }
        //파일이 없다면 없다고 리턴
        return Optional.empty();
    }

    private String postS3(File targetFile, String fileName) {
        amazonS3Client.putObject(new PutObjectRequest(bucket, fileName, targetFile)
                .withCannedAcl(CannedAccessControlList.PublicRead));
        logger.info("File posted : " + fileName);

        return amazonS3Client.getUrl(bucket, fileName).toString();
    }

    private void deleteLocalFile(File targetFile){
        if(targetFile.delete()) {
            logger.info("File delete success");
            return;
        }
        logger.error("File delete Error in S3 utils");
    }
}
