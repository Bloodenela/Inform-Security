package org.example;

import org.bytedeco.javacv.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class VideoSteganography {

    public static void main(String[] args) throws IOException {
        // Загрузите видеофайл
        File videoFile = new File("test.mp4");

        // Считайте информацию, которую хотите скрыть
        String secretMessage = "This is sparta";

        // Скройте сообщение в видео
        hideMessage(videoFile, secretMessage);

        // Извлеките сообщение из видео
        String extractedMessage = extractMessage(videoFile);
        System.out.println("Извлеченное сообщение: " + extractedMessage);
    }

    private static void hideMessage(File videoFile, String message) throws IOException {
        // Прочитайте видеофайл в байтовый массив
        byte[] videoData = Files.readAllBytes(videoFile.toPath());

        // Преобразуйте сообщение в байтовый массив
        byte[] messageData = message.getBytes();
        videoData[0] = (byte) messageData.length;
        // Вставьте сообщение в видеоданные
        for (int i = 0; i < messageData.length; i++) {
            videoData[(i+1) * 8] = messageData[i];
        }

        // Запишите измененные видеоданные обратно в файл
        Files.write(videoFile.toPath(), videoData);
    }

    private static String extractMessage(File videoFile) throws IOException {
        // Прочитайте видеофайл в байтовый массив
        byte[] videoData = Files.readAllBytes(videoFile.toPath());
        // Найдите первый байт, отличный от 0
        int start = -1;
        for (int i = 0; i < videoData.length; i++) {
            if (videoData[i] != 0) {
                start = i;
                break;
            }
        }
        // Если не найден ни один ненулевой байт, значит сообщения нет
        if (start == -1) {
            return "";
        }

        // Определите длину сообщения
//        int messageLength = 0;
//        for (int i = start; i < videoData.length; i++) {
//            if ((i - start) % 8 == 0) {
//                messageLength++;
//            }
//            if (messageLength == 256) { // 256 - максимальная длина сообщения в байтах
//                break;
//            }
//        }

        int messageLength = (int)videoData[0]+1;
        System.out.println(messageLength);
        // Извлеките сообщение из видеоданных
        StringBuilder message = new StringBuilder();
        for (int i = start; i < start + messageLength * 8; i++) {
            if ((i - start) % 8 == 0 && (i-start) != 0) {
                char c = (char)(videoData[i]);
                System.out.println(c);
                message.append(c);
            }
        }
        return message.toString();
    }
}