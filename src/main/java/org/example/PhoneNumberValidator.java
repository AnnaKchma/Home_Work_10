package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneNumberValidator {

    public static void main(String[] args) {
        String inputFileName = "file.txt";

        try {
            String content = new String(Files.readAllBytes(Paths.get(inputFileName)));
            printValidPhoneNumbers(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void printValidPhoneNumbers(String content) {
        String[] lines = content.split("\\r?\\n");

        Pattern pattern = Pattern.compile("\\(\\d{3}\\) \\d{3}-\\d{4}|\\d{3}-\\d{3}-\\d{4}");

        for (String line : lines) {
            Matcher matcher = pattern.matcher(line);
            if (matcher.matches()) {
                System.out.println(line);
            }
        }
    }
}
