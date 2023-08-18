package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class User {
    private final String name;
    private final int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public static void main(String[] args) {
        String inputFileName = "file1.txt";
        String outputFileName = "user.json";

        List<User> users = readUsersFromFile(inputFileName);
        writeUsersToJsonFile(users, outputFileName);
    }

    public static List<User> readUsersFromFile(String fileName) {
        List<User> users = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            String[] titles = null;

            while ((line = reader.readLine()) != null) {
                String[] columns = line.split(" ");

                if (titles == null) {
                    titles = columns;
                    continue;
                }

                String name = columns[0];
                int age = Integer.parseInt(columns[1]);

                users.add(new User(name, age));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return users;
    }

    public static void writeUsersToJsonFile(List<User> users, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write("[\n");
            for (int i = 0; i < users.size(); i++) {
                User user = users.get(i);
                writer.write("    {\n");
                writer.write("        \"name\": \"" + user.name + "\",\n");
                writer.write("        \"age\": " + user.age + "\n");
                writer.write("    }");
                if (i < users.size() - 1) {
                    writer.write(",");
                }
                writer.write("\n");
            }
            writer.write("]\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

