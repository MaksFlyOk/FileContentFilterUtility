import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import ru.shift.utility.core.FilesWriter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class FilesWriterTest {

  @TempDir
  Path tempDir;

  @Test
  public void testLineWrite() throws IOException {
    Path outputDir = tempDir;
    FilesWriter writer = new FilesWriter("prefix", outputDir, false);

    writer.lineWrite("123", "integer");
    writer.lineWrite("3.14", "float");
    writer.lineWrite("Lorem ipsum", "string");

    Path integerFile = outputDir.resolve("prefix-integer.txt");
    Path floatFile = outputDir.resolve("prefix-float.txt");
    Path stringFile = outputDir.resolve("prefix-string.txt");

    assertTrue(Files.exists(integerFile));
    assertTrue(Files.exists(floatFile));
    assertTrue(Files.exists(stringFile));

    List<String> integerLines = Files.readAllLines(integerFile);
    List<String> floatLines = Files.readAllLines(floatFile);
    List<String> stringLines = Files.readAllLines(stringFile);

    assertEquals(1, integerLines.size());
    assertEquals("123", integerLines.get(0));

    assertEquals(1, floatLines.size());
    assertEquals("3.14", floatLines.get(0));

    assertEquals(1, stringLines.size());
    assertEquals("Lorem ipsum", stringLines.get(0));
  }
}
