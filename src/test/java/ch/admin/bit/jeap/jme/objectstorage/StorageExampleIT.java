package ch.admin.bit.jeap.jme.objectstorage;

import ch.admin.bit.jeap.jme.test.BootServiceSpringIntegrationTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class StorageExampleIT extends BootServiceSpringIntegrationTestBase {

    private static final String SERVICE_BASE_URL = "http://localhost:8080/jme-object-storage-example";

    @BeforeAll
    static void startServices() throws Exception {
        startService(SERVICE_BASE_URL);
    }

    @Test
    void saveAndReadFileInBucket() {
        final String newBucketName = "junit-test-" + UUID.randomUUID();

        Response createBucketResponse = given().baseUri(SERVICE_BASE_URL).contentType(ContentType.JSON)
                .when().put("/api/buckets?bucketName=" + newBucketName);

        assertThat(createBucketResponse.getStatusCode()).isEqualTo(HttpStatus.OK.value());

        Response getBucketResponse = given().baseUri(SERVICE_BASE_URL).contentType(ContentType.JSON)
                .when().get("/api/buckets?bucketName=" + newBucketName);

        assertThat(getBucketResponse.getStatusCode()).isEqualTo(HttpStatus.OK.value());
        assertThat(getBucketResponse.getBody().asString()).contains(newBucketName);

        Response saveFileResponse = given().baseUri(SERVICE_BASE_URL).contentType(ContentType.JSON)
                .when().put("/api/buckets/" + newBucketName + "/files/random");
        assertThat(saveFileResponse.getStatusCode()).isEqualTo(HttpStatus.OK.value());
        String fileKey = saveFileResponse.getBody().asString();

        Response deleteFileResponse = given().baseUri(SERVICE_BASE_URL).contentType(ContentType.JSON)
                .when().delete("/api/buckets/" + newBucketName + "/files?key=" + fileKey);
        assertThat(deleteFileResponse.getStatusCode()).isEqualTo(HttpStatus.OK.value());

        Response deleteBucketResponse = given().baseUri(SERVICE_BASE_URL).contentType(ContentType.JSON)
                .when().delete("/api/buckets?bucketName=" + newBucketName);

        assertThat(deleteBucketResponse.getStatusCode()).isEqualTo(HttpStatus.OK.value());

    }


}
