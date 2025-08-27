package ru.shift.utility;

import org.apache.commons.cli.*;
import ru.shift.utility.cli.Params;

import static ru.shift.utility.cli.ArgumentParser.parseArgs;

public class FileContentFilterUtility {
  public static void main(String[] args) {
    try {
      Params params = parseArgs(args);

    } catch (ParseException e) {
      System.err.println("Error: " + e.getMessage());
      System.exit(2);
    } catch (Exception e) {
      System.err.println("Unexpected error occurred: " + e.getMessage());
      e.printStackTrace();
      System.exit(3);
    }
  }
}