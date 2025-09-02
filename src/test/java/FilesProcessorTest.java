import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import ru.shift.utility.core.FilesProcessor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class FilesProcessorTest {

  @TempDir
  Path tempDir;

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

  @Test
  public void testFileHandlingEmptyFile() throws IOException {
    Path testFile = tempDir.resolve("empty.txt");
    Files.write(testFile, List.of());

    List<String> lines = FilesProcessor.fileHandling(List.of(testFile));

    assertTrue(lines.isEmpty());
  }
}
