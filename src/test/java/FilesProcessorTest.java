import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import ru.shift.utility.core.FilesProcessor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

/**
 * The {@link FilesProcessorTest} class is required to test the {@link FilesProcessor} class
 */
public class FilesProcessorTest {
  /**
   * Temporary directory required for tests
   */
  @TempDir
  Path tempDir;

  /**
   * Test of method {@link FilesProcessor#fileHandling(List)} of class {@link FilesProcessor} with non-empty input file
   * @throws IOException  When there is an error reading the file
   */
  @Test
  public void testFileHandling() throws IOException {
    Path testFile = tempDir.resolve("test.txt");
    Files.write(testFile, List.of("123", "3.14", "Lorem ipsum"));

    List<String> lines = FilesProcessor.fileHandling(List.of(testFile));

    assertEquals(3, lines.size());
    assertTrue(lines.contains("123"));
    assertTrue(lines.contains("3.14"));
    assertTrue(lines.contains("Lorem ipsum"));
  }

  /**
   * Test of method {@link FilesProcessor#fileHandling(List)} of class {@link FilesProcessor} with empty input file
   * @throws IOException  When there is an error reading the file
   */
  @Test
  public void testFileHandlingEmptyFile() throws IOException {
    Path testFile = tempDir.resolve("empty.txt");
    Files.write(testFile, List.of());

    List<String> lines = FilesProcessor.fileHandling(List.of(testFile));

    assertTrue(lines.isEmpty());
  }
}
