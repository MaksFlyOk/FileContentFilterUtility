package ru.shift.utility.core;

/**
 * Enumeration of string types used to classify data from files.
 * <p>
 * Defines the possible data types that can be found in files: {@code integers}, {@code float}, and {@code strings}.
 */
public enum LineType {
  /**
   * Integer
   * <p>
   * Examples: {@code "123"}, {@code "-456"}.
   */
  INTEGER,
  /**
   * Float
   * <p>
   * Examples: {@code "-3.1415"}, {@code "1.528537E-25"}.
   */
  FLOAT,
  /**
   * String
   * <p>
   * Examples: {@code "long"}, {@code "Lorem ipsum"}.
   */
  STRING
}
