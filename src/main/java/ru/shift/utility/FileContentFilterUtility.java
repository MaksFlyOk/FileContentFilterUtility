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

public class FileContentFilterUtility {
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