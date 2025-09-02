package ru.shift.utility.cli;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * The ArgumentParser class is responsible for methods of parsing command line arguments
 */
public class ArgumentParser {
  /**
   * Parses command line arguments
   * @param args Command line arguments
   * @return CommandLine instance with options parsed
   * @throws ParseException If the arguments are incorrect
   */
  public static CommandLine parse(String[] args) throws ParseException {
    Options options = new Options();
    options.addOption("o", "-output", true, "Path to results");
    options.addOption("p", "-prefix", true, "Prefix for file names");
    options.addOption("a", "-append", false, "Append the result to the existing file or replace it with new data");
    options.addOption("s", "-short", false, "Short statistic");
    options.addOption("f", "-full", false, "Full statistic");
    options.addOption("h", "-help", false, "Print this help message");

    try {
      CommandLine commandLine = new DefaultParser().parse(options, args);

      if (commandLine.hasOption("h")) {
        printHelp(options);
        System.exit(0);
      }

      if(commandLine.getArgList().isEmpty()) throw new ParseException("Not enough files specified");

      return commandLine;
    } catch (ParseException e) {
      if(args.length > 0) System.out.println(e.getMessage());
      else {
        printHelp(options);
      }
      throw e;
    }
  }

  /**
   * Generates an instance of the passed command line parameters
   * @param args Command line arguments
   * @return Params instance with options passed
   * @throws ParseException If the arguments are incorrect
   */
  public static Params parseArgs(String[] args)
          throws ParseException
  {
    CommandLine commandLine = parse(args);

    List<String> fileNames = commandLine.getArgList();
    if (fileNames.isEmpty()) {
      throw new ParseException("No input files specified.");
    }

    List<Path> inputFiles = fileNames.stream()
            .map(Paths::get)
            .toList();

    Path outputPath = commandLine.hasOption("o") ? Paths.get(commandLine.getOptionValue("o")) : null;
    if (outputPath != null && (!Files.isDirectory(outputPath) || !Files.exists(outputPath))) {
      throw new ParseException("Uncorrected output directory: " + outputPath);
    }

    String prefix = commandLine.hasOption("p")
            ? commandLine.getOptionValue("p")
            : null;

    return new Params(
            commandLine.hasOption("o"),
            outputPath,
            commandLine.hasOption("p"),
            prefix,
            commandLine.hasOption("a"),
            commandLine.hasOption("s"),
            commandLine.hasOption("f"),
            inputFiles
    );
  }

  /**
   * This method is required to output help
   * @param options Represents a collection of {@link Options} objects, which describe the possible options for a command-line
   */
  private static void printHelp(Options options) {
    String header = """
            File Content Filter Utility

            This app filters content from input files into separate files for integers, floats, and strings.
            Usage: FileContentFilterUtility [options] <input files>""";
    String footer = """

            Examples of use:
              FileContentFilterUtility file1.txt file2.txt
              FileContentFilterUtility -o /output -p prefix file1.txt
              FileContentFilterUtility -a -f file1.txt file2.txt""";

    HelpFormatter formatter = new HelpFormatter();
    formatter.printHelp("FileContentFilterUtility", header, options, footer, true);
  }
}
