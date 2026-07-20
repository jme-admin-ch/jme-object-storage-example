package ch.admin.bit.jeap.jme.objectstorage.web;

import ch.admin.bit.jeap.jme.objectstorage.domain.File;
import ch.admin.bit.jeap.jme.objectstorage.domain.FileGenerator;
import ch.admin.bit.jeap.jme.objectstorage.domain.S3BucketObjectService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/buckets/{bucketName}/files")
@Slf4j
@RequiredArgsConstructor
@Tag(name = "FileController", description = "Bucket name on aws: jme-object-storage-example-obs-nivel-dev")
public class FileController {

    private final S3BucketObjectService s3BucketObjectService;

    @PutMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public String create(@PathVariable String bucketName, @RequestParam("file") MultipartFile multipartFile) throws IOException {
        String key = s3BucketObjectService.putObject(bucketName, File.from(multipartFile));
        log.info("Object with the key {} was put in the bucket '{}'", key, bucketName);
        return key;
    }

    @PutMapping("/random")
    public String createRandom(@PathVariable String bucketName) {
        String key = s3BucketObjectService.putObject(bucketName, FileGenerator.generateRandomFile(100));
        log.info("Random object with the key '{}' was put in the bucket '{}'", key, bucketName);
        return key;
    }

    @GetMapping
    public List<String> findAll(@PathVariable String bucketName) {
        return s3BucketObjectService.getObjects(bucketName);
    }

    @GetMapping("/{key}")
    public ResponseEntity<Resource> findByKey(@PathVariable String bucketName, @PathVariable String key) {
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(new InputStreamResource(s3BucketObjectService.getObject(bucketName, key)));
    }

    @DeleteMapping
    public void deleteByName(@PathVariable String bucketName, String key) {
        s3BucketObjectService.deleteObject(bucketName, key);
        log.info("Object with key '{}' from bucket '{}' deleted", key, bucketName);
    }
}
