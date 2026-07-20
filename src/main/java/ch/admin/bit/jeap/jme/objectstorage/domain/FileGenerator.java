package ch.admin.bit.jeap.jme.objectstorage.domain;

import java.nio.ByteBuffer;
import java.security.SecureRandom;
import java.util.Random;
import java.util.UUID;

public class FileGenerator {

    static final Random RANDOM = new SecureRandom();

    public static File generateRandomFile(int fileSize) {
            String fileName = "file_" + UUID.randomUUID() + ".txt";
            ByteBuffer fileContent = generateRandomBytes(fileSize);
            return new File(fileName, fileContent);
    }

    private static ByteBuffer generateRandomBytes(int size) {
        byte[] array = new byte[size];
        RANDOM.nextBytes(array);
        return ByteBuffer.wrap(array);
    }
}
