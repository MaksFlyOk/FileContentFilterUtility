package ru.shift.utility.core;

import java.util.regex.Pattern;

public class LineClassifier {
  private static final Pattern INTEGER_PATTERN = Pattern.compile("\\b-?\\d+\\b");
  private static final Pattern FLOAT_PATTERN = Pattern.compile("-?\\d+(?:[.,]\\d*)?(?:[eE][+-]?\\d+)?\\b");

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
