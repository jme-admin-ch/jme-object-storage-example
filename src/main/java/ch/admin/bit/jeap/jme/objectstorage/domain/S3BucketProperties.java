package ch.admin.bit.jeap.jme.objectstorage.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "jeap.s3.bucket-names")
public class S3BucketProperties {

    private String exampleBucket1;
}
