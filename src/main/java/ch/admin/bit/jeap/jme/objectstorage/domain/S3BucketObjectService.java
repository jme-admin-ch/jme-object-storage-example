package ch.admin.bit.jeap.jme.objectstorage.domain;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;

import java.io.InputStream;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class S3BucketObjectService {

    private final S3Client s3Client;

    public String putObject(String bucketName, File inMemoryObject) {
        PutObjectRequest request = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(inMemoryObject.getName())
                .build();
        s3Client.putObject(request, RequestBody.fromByteBuffer(inMemoryObject.getContent()));
        return inMemoryObject.getName();
    }

    public List<String> getObjects(String bucketName) {
        ListObjectsV2Request listObjectsV2Request = ListObjectsV2Request.builder()
                .bucket(bucketName)
                .build();

        return s3Client.listObjectsV2(listObjectsV2Request).contents().stream().map(S3Object::key).toList();
    }

    public InputStream getObject(String bucketName, String objectKey) {
        GetObjectRequest objectRequest = GetObjectRequest.builder()
                .bucket(bucketName)
                .key(objectKey)
                .build();

        return s3Client.getObject(objectRequest);
    }

    public void deleteObject(String bucketName, String objectKey) {
        DeleteObjectRequest deleteObjectRequest = DeleteObjectRequest.builder()
                .bucket(bucketName)
                .key(objectKey)
                .build();

        s3Client.deleteObject(deleteObjectRequest);
    }
}
