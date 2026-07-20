package ch.admin.bit.jeap.jme.objectstorage.web;

import ch.admin.bit.jeap.jme.objectstorage.ObjectStorageExampleApplication;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition
@Configuration
public class OpenApiConfig {

    @Bean
    GroupedOpenApi api() {
        return GroupedOpenApi.builder()
                .group("API")
                .pathsToMatch("/api/**")
                .packagesToScan(ObjectStorageExampleApplication.class.getPackageName())
                .build();
    }

}
