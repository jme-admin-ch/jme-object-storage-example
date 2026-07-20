package ch.admin.bit.jeap.jme.objectstorage.domain;

import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.ByteBuffer;

@Getter
public class File {
    private String name;
    private ByteBuffer content;

    public File(String name, ByteBuffer content) {
        this.name = name;
        this.content = content;
    }

    public static File from(MultipartFile multipartFile) throws IOException {
        return new File(multipartFile.getOriginalFilename(), ByteBuffer.wrap(multipartFile.getBytes()));
    }
}
