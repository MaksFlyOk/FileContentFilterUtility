package ru.shift.utility.core;

import java.util.regex.Pattern;

/**
 * The LineClassifier class is responsible for classifying lines
 */
public class LineClassifier {
  /**
   * Regular expression pattern for detecting {@code integers}
   */
  private static final Pattern INTEGER_PATTERN = Pattern.compile("\\b-?\\d+\\b");
  /**
   * Regular expression pattern for detecting {@code float}
   */
  private static final Pattern FLOAT_PATTERN = Pattern.compile("-?\\d+(?:[.,]\\d*)?(?:[eE][+-]?\\d+)?\\b");

  /**
   * The method classifies the passed string according to {@link LineType}
   * @param line The string whose type needs to be determined
   * @return Returns the type of the passed line according to {@link LineType}
   */
  public static LineType getType(String line) {
    if (line.isBlank()) return null;

    if (INTEGER_PATTERN.matcher(line).matches()) {
      return LineType.INTEGER;
    }
    if (FLOAT_PATTERN.matcher(line).matches()) {
      return LineType.FLOAT;
    }

    return LineType.STRING;
  }
}
