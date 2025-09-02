package ru.shift.utility;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import ru.shift.utility.cli.Params;
import ru.shift.utility.core.FilesProcessor;
import ru.shift.utility.core.FilesWriter;
import ru.shift.utility.core.LineClassifier;
import ru.shift.utility.core.LineType;
import ru.shift.utility.statistic.FullStatistics;
import ru.shift.utility.statistic.Statistics;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;
import static ru.shift.utility.cli.ArgumentParser.parseArgs;

/**
 * The {@link FileContentFilterUtilityTest} class is required for integration testing of the utility
 */
public class FileContentFilterUtilityTest {
  /**
   * Temporary directory required for tests
   */
  @TempDir
  Path tempDir;

  /**
   * This test is an integration test and is aimed at testing the operation of the utility
   * @throws IOException When an error occurs related to reading/writing to files
   */
  @Test
  public void testMainLogic() throws IOException {
    Path inputFile = tempDir.resolve("input.txt");
    Files.write(inputFile, List.of("123", "3.14", "Lorem ipsum", "-456", "-0.001", "Another string"));

    String[] args = {inputFile.toString(), "-o", tempDir.toString(), "-f"};

    assertDoesNotThrow(() -> {
      Params params = parseArgs(args);

      final List<String> handledFiles = FilesProcessor.fileHandling(params.files());

      FilesWriter filesWriter = new FilesWriter(params.filePrefix(), params.writeFilesPath(), params.aEnabled());
      Statistics statistic = params.fEnabled() ? new FullStatistics() : new Statistics();

      for (String line : handledFiles) {
        LineType lineType = LineClassifier.getType(line);

        filesWriter.lineWrite(line, lineType.toString().toLowerCase());
        statistic.processLine(lineType, line);
      }

      if (params.sEnabled() || params.fEnabled()) statistic.printStatistics();
    });

    Path integerFile = tempDir.resolve("integer.txt");
    Path floatFile = tempDir.resolve("float.txt");
    Path stringFile = tempDir.resolve("string.txt");

    assertTrue(Files.exists(integerFile));
    assertTrue(Files.exists(floatFile));
    assertTrue(Files.exists(stringFile));

    List<String> integerLines = Files.readAllLines(integerFile);
    List<String> floatLines = Files.readAllLines(floatFile);
    List<String> stringLines = Files.readAllLines(stringFile);

    assertEquals(2, integerLines.size());
    assertTrue(integerLines.contains("123"));
    assertTrue(integerLines.contains("-456"));

    assertEquals(2, floatLines.size());
    assertTrue(floatLines.contains("3.14"));
    assertTrue(floatLines.contains("-0.001"));

    assertEquals(2, stringLines.size());
    assertTrue(stringLines.contains("Lorem ipsum"));
    assertTrue(stringLines.contains("Another string"));
  }
}
