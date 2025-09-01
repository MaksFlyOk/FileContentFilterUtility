package ru.shift.utility;

import org.apache.commons.cli.*;
import ru.shift.utility.cli.Params;
import ru.shift.utility.core.FilesProcessor;
import ru.shift.utility.core.FilesWriter;
import ru.shift.utility.core.LineClassifier;
import ru.shift.utility.core.LineType;
import ru.shift.utility.statistic.FullStatistics;
import ru.shift.utility.statistic.Statistics;

import java.util.List;

import static ru.shift.utility.cli.ArgumentParser.parseArgs;

/**
 * The main utility class for filtering and classifying data from files
 * <p>
 * The utility is designed to read data from input files, classify each line into integers, real numbers or strings,
 * write the filtered data to separate output files and collect statistics for each data type
 * <p>
 * Supported options:
 * <pre>
 * - Specifying the path for output files via the -o option
 * - Specifying a prefix for output file names via the -p option
 * - The mode of adding data to existing files via the -a option
 * - Outputting brief statistics via the -s option
 * - Outputting full statistics via the -f option
 * </pre>
 * Examples of use:
 * <pre>
 * FileContentFilterUtility file1.txt file2.txt
 * FileContentFilterUtility -o /output -p prefix file1.txt
 * FileContentFilterUtility -a -f file1.txt file2.txt
 * </pre>
 */
public class FileContentFilterUtility {
  /**
   * Entry point to the application
   * The method processes command line arguments, filters data from input files, writes filtered data to the
   * corresponding output files, and outputs statistics if requested via command line options
   *
   * @param args command line arguments containing paths to input files and options
   */
  public static void main(String[] args) {
    try {
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
    } catch (ParseException e) {
      System.exit(2);
    } catch (Exception e) {
      System.err.println("Unexpected error occurred: " + e.getMessage());
      System.exit(3);
    }
  }
}