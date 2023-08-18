package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class WordFrequencyCalculator {

    public static void main(String[] args) {
        String fileName = "words.txt";
        Map<String, Integer> wordFrequencyMap = calculateWordFrequency(fileName);

        // Find the most repeated word
        String mostRepeatedWord = findMostRepeatedWord(wordFrequencyMap);
        System.out.println("Most repeated word: " + mostRepeatedWord);

        // Sort words by frequency and print
        List<Map.Entry<String, Integer>> sortedWords = sortWordsByFrequency(wordFrequencyMap);
        for (Map.Entry<String, Integer> entry : sortedWords) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }

    public static Map<String, Integer> calculateWordFrequency(String fileName) {
        Map<String, Integer> wordFrequencyMap = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] words = line.split("\\s+"); // Split by one or more spaces
                for (String word : words) {
                    if (!word.isEmpty()) {
                        wordFrequencyMap.put(word, wordFrequencyMap.getOrDefault(word, 0) + 1);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return wordFrequencyMap;
    }

    public static String findMostRepeatedWord(Map<String, Integer> wordFrequencyMap) {
        String mostRepeatedWord = null;
        int maxFrequency = 0;

        for (Map.Entry<String, Integer> entry : wordFrequencyMap.entrySet()) {
            if (entry.getValue() > maxFrequency) {
                maxFrequency = entry.getValue();
                mostRepeatedWord = entry.getKey();
            }
        }

        return mostRepeatedWord;
    }

    public static List<Map.Entry<String, Integer>> sortWordsByFrequency(Map<String, Integer> wordFrequencyMap) {
        List<Map.Entry<String, Integer>> sortedList = new ArrayList<>(wordFrequencyMap.entrySet());

        sortedList.sort((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()));

        return sortedList;
    }
}
