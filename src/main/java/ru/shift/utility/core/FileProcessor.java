package ru.shift.utility.core;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

public class FileProcessor {
  public static List<String> fileHandling(List<Path> files) {
    return files.stream().flatMap(file -> {
      try {
        return Files.lines(file, StandardCharsets.UTF_8);
      } catch (IOException e) {
        System.err.println("Error reading file: " + file + " - " + e.getMessage());
        return Stream.empty();
      }
    }).toList();
  }
}
