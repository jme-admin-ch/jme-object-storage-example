package ch.admin.bit.jeap.jme.objectstorage;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

@SpringBootApplication
@Slf4j
public class ObjectStorageExampleApplication {

    static void main(String[] args) {
        Environment env = SpringApplication.run(ObjectStorageExampleApplication.class, args).getEnvironment();

        log.info("""

                        ----------------------------------------------------------
                        \t{} is running!\s
                        \t
                        \tSwaggerUI: \thttp://localhost:{}{}/swagger-ui.html
                        \tProfile(s): \t{}
                        ----------------------------------------------------------""",
                env.getProperty("spring.application.name"),
                env.getProperty("server.port"),
                env.getProperty("server.servlet.context-path"),
                env.getActiveProfiles());
    }
}
