package ru.shift.utility.core;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

/**
 * The FilesWriter class is responsible for writing strings to files
 */
public class FilesWriter {
  /**
   * Output file name prefix
   */
  private final String prefix;
  /**
   * Path to results
   */
  private final Path outputPath;

  /**
   * Constructor of the FilesWriter class
   * This constructor sets the passed value of {@link #outputPath}, if prefix is not {@code null}, then sets it with
   * the suffix {@code "-"}, and also processes the {@param appendMode} parameter, if it is {@code true}, then
   * clears the files with the results in the specified {@link #outputPath} directory
   * @param prefix Output file name prefix
   * @param outputPath Path to results
   * @param appendMode Activation of the option to add the result to existing files
   */
  public FilesWriter(String prefix, Path outputPath, boolean appendMode) {
    this.outputPath = outputPath;
    this.prefix = prefix == null ? "" : prefix + "-";

    if(!appendMode) {
      clearClassifierFile(LineType.INTEGER.toString().toLowerCase());
      clearClassifierFile(LineType.FLOAT.toString().toLowerCase());
      clearClassifierFile(LineType.STRING.toString().toLowerCase());
    }
  }

  /**
   * The method allows you to write the passed string to a file with the passed name
   * @param line The line that needs to be written
   * @param fileName The name of the file in which you want to make a recording
   */
  public void lineWrite(String line, String fileName) {
    Path outputFileClassifier = getOutputFileClassifierPath(fileName);
    String writeLine = line + System.lineSeparator();

    try{
       Files.write(outputFileClassifier, writeLine.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
    } catch (IOException e) {
      System.err.println("Error writing line: " + line + " to file: " + outputFileClassifier);
    }
  }

  /**
   * This method is necessary to clean the file
   * @param fileName The name of the file to be cleared
   */
  private void clearClassifierFile(String fileName) {
    final Path filePath = getOutputFileClassifierPath(fileName);

    try {
      Files.deleteIfExists(filePath);
    } catch (IOException e) {
      System.err.println("Unable to clear file: " + filePath + " because it is not accessible");
    }
  }

  /**
   * This method is needed to get the full path to a specific file to account for: {@link #outputPath}, {@link #prefix}.
   * @param fileName The name of the file to which the path is created
   * @return Final path to the file
   */
  private Path getOutputFileClassifierPath(String fileName) {
    return Path.of((outputPath == null ? "" : outputPath) + "\\" + prefix + fileName + ".txt");
  }
}
