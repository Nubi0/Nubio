package com.enjoyservice.global.util;

import org.springframework.http.ContentDisposition;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

public class FileUtils {

    private static final String FILE_EXTENSION_SEPARATOR = ".";
    private static final String CATEGORY_PREFIX = "/";
    private static final String NAME_SEPARATOR = "_";

    public static String buildFileName(String category, String originalFileName, UUID uuid) {
        // 파일 확장자
        int fileExtensionIndex = originalFileName.lastIndexOf(FILE_EXTENSION_SEPARATOR);
        String fileExtension = originalFileName.substring(fileExtensionIndex);
        // 파일 이름
        String fileName = originalFileName.substring(0, fileExtensionIndex);

        return category +  CATEGORY_PREFIX + fileName + NAME_SEPARATOR + uuid + fileExtension;
    }

}
