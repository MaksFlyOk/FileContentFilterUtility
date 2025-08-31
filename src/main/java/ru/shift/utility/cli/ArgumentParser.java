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
    options.addOption("o", "output", true, "Path to results");
    options.addOption("p", "prefix", true, "Prefix for file names");
    options.addOption("a", "append", false, "Append the result to the existing file or replace it with new data");
    options.addOption("s", "short", false, "Short statistic");
    options.addOption("f", "full", false, "Full statistic");

    try {
      CommandLine commandLine = new DefaultParser().parse(options, args);

      if(commandLine.getArgList().isEmpty()) throw new ParseException("Not enough files specified");

      return commandLine;
    } catch (ParseException e) {
      if(args.length > 0) System.out.println(e.getMessage());
      else {
        String header = "This app is a task for Shift";
        String footer = "";

        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("FileContentFilterUtility", header, options, footer, true);
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
}
