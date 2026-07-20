package ch.admin.bit.jeap.jme.objectstorage.web;

import ch.admin.bit.jeap.jme.objectstorage.domain.S3BucketService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/buckets")
@Slf4j
@RequiredArgsConstructor
@ConditionalOnProperty(name = "jeap.s3.bucket-management.enabled", havingValue = "true")
@Tag(name = "BucketController")
public class BucketController {

    private final S3BucketService s3BucketService;

    @PutMapping
    public void create(String bucketName) {
        s3BucketService.createBucket(bucketName);
        log.info("Bucket '{}' created", bucketName);
    }

    @GetMapping
    public List<String> findAll() {
        return s3BucketService.listBuckets();
    }

    @DeleteMapping
    public void deleteByName(String bucketName) {
        s3BucketService.deleteBucket(bucketName);
        log.info("Bucket with id '{}' deleted", bucketName);
    }
}
