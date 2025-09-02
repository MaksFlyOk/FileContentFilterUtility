package ru.shift.utility;

import org.apache.commons.cli.ParseException;
import org.junit.jupiter.api.Test;
import ru.shift.utility.cli.ArgumentParser;
import ru.shift.utility.cli.Params;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

/**
 * The {@link ArgumentParserTest} class is required to test the {@link ArgumentParser} class
 */
public class ArgumentParserTest {

  /**
   * Test of {@link ArgumentParser#parseArgs(String[])} method of class {@link ArgumentParser} with multiple options
   * @throws ParseException  If the arguments are incorrect
   */
  @Test
  public void testParseArgs() throws ParseException {
    String[] args = {"-o", "./src/test/resources", "-p", "prefix", "-a", "-f", "file1.txt", "file2.txt"};
    Params params = ArgumentParser.parseArgs(args);

    assertTrue(params.oEnabled());
    assertEquals(Paths.get("./src/test/resources"), params.writeFilesPath());
    assertTrue(params.pEnabled());
    assertEquals("prefix", params.filePrefix());
    assertTrue(params.aEnabled());
    assertTrue(params.fEnabled());
    assertFalse(params.sEnabled());

    List<Path> files = params.files();
    assertEquals(2, files.size());
    assertEquals(Paths.get("file1.txt"), files.get(0));
    assertEquals(Paths.get("file2.txt"), files.get(1));
  }

  /**
   * Test of method {@link ArgumentParser#parseArgs(String[])} of class {@link ArgumentParser} without options,
   * only with input files
   * @throws ParseException  If the arguments are incorrect
   */
  @Test
  public void testParseArgsNoOptions() throws ParseException {
    String[] args = {"file1.txt", "file2.txt"};
    Params params = ArgumentParser.parseArgs(args);

    assertFalse(params.oEnabled());
    assertNull(params.writeFilesPath());
    assertFalse(params.pEnabled());
    assertNull(params.filePrefix());
    assertFalse(params.aEnabled());
    assertFalse(params.fEnabled());
    assertFalse(params.sEnabled());

    List<Path> files = params.files();
    assertEquals(2, files.size());
    assertEquals(Paths.get("file1.txt"), files.get(0));
    assertEquals(Paths.get("file2.txt"), files.get(1));
  }

  /**
   * Test method {@link ArgumentParser#parseArgs(String[])} of class {@link ArgumentParser} if options and
   * input files are not specified
   */
  @Test
  public void testParseArgsEmpty() {
    String[] args = {};
    assertThrows(ParseException.class, () -> ArgumentParser.parseArgs(args));
  }
}
