package ru.shift.utility.core;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class FilesWriter {
  private final String prefix;
  private final Path outputPath;

  public FilesWriter(String prefix, Path outputPath, boolean appendMode) {
    this.outputPath = outputPath;
    this.prefix = prefix == null ? "" : prefix + "-";

    if(!appendMode) {
      clearClassifierFile(LineType.INTEGER.toString().toLowerCase());
      clearClassifierFile(LineType.FLOAT.toString().toLowerCase());
      clearClassifierFile(LineType.STRING.toString().toLowerCase());
    }
  }

  public void lineWrite(String line, String fileName) {
    Path outputFileClassifier = getOutputFileClassifierPath(fileName);
    String writeLine = line + System.lineSeparator();

    try{
       Files.write(outputFileClassifier, writeLine.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
    } catch (IOException e) {
      System.err.println("Error writing line: " + line + " to file: " + outputFileClassifier);
    }
  }

  private void clearClassifierFile(String fileName) {
    final Path filePath = getOutputFileClassifierPath(fileName);

    try {
      Files.deleteIfExists(filePath);
    } catch (IOException e) {
      System.err.println("Unable to clear file: " + filePath + " because it is not accessible");
    }
  }

  private Path getOutputFileClassifierPath(String fileName) {
    return Path.of((outputPath == null ? "" : outputPath) + "\\" + prefix + fileName + ".txt");
  }
}
