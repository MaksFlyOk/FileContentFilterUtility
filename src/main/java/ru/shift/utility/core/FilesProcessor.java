package ru.shift.utility.core;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * The FilesProcessor class contains methods for processing input files
 */
public class FilesProcessor {
  /**
   * Combines lines from all files into a single {@link List}
   * @param files {@link List} of input file paths
   * @return {@link List} containing all lines
   */
  public static List<String> fileHandling(List<Path> files) {
    return files.stream().flatMap(file -> {
      try {
        return Files.lines(file, StandardCharsets.UTF_8);
      } catch (IOException e) {
        System.err.println("Error reading file: " + file + " - " + e.getMessage());
        return java.util.stream.Stream.empty();
      }
    }).toList();
  }
}
