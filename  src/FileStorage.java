package com.lendingadvisor;

import java.io.*;

public class FileStorage {
    public static void save(String data) {
        try (FileWriter writer = new FileWriter("data/data.txt", true)) {
            writer.write(data + "\n");
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    public static void load() {
        try (BufferedReader reader = new BufferedReader(new FileReader("data/data.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading data: " + e.getMessage());
        }
    }
}
