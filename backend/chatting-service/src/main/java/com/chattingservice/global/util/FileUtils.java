package com.chattingservice.global.util;

import java.time.LocalDateTime;
import java.util.UUID;

public class FileUtils {
    private static final String FILE_EXTENSION_SEPARATOR = ".";
    private static final String CATEGORY_PREFIX = "/";
    private static final String NAME_SEPARATOR = "_";
    private static final String TIME_SEPARATOR = "_";

    public static String buildFileName(String category, String originalFileName, UUID uuid) {
        // 파일 확장자
        int fileExtensionIndex = originalFileName.lastIndexOf(FILE_EXTENSION_SEPARATOR);
        String fileExtension = originalFileName.substring(fileExtensionIndex);
        // 파일 이름
        String fileName = originalFileName.substring(0, fileExtensionIndex);

        return category + CATEGORY_PREFIX + fileName + NAME_SEPARATOR + uuid + fileExtension;
    }

    public static String buildFileNameForParticipant(String category, Long room, Long participantId, String originalFileName, LocalDateTime now) {
        // 파일 확장자
        int fileExtensionIndex = originalFileName.lastIndexOf(FILE_EXTENSION_SEPARATOR);
        String fileExtension = originalFileName.substring(fileExtensionIndex);
        // 파일 이름
        String fileName = originalFileName.substring(0, fileExtensionIndex);

        return category + CATEGORY_PREFIX + room + CATEGORY_PREFIX + participantId + CATEGORY_PREFIX + fileName + TIME_SEPARATOR + now + fileExtension;
    }

    public static String buildFileNameForRoom(String category, Long room, String originalFileName, LocalDateTime now) {
        // 파일 확장자
        int fileExtensionIndex = originalFileName.lastIndexOf(FILE_EXTENSION_SEPARATOR);
        String fileExtension = originalFileName.substring(fileExtensionIndex);
        // 파일 이름
        String fileName = originalFileName.substring(0, fileExtensionIndex);

        return category + CATEGORY_PREFIX + room + CATEGORY_PREFIX + fileName + TIME_SEPARATOR + now + fileExtension;
    }

}
