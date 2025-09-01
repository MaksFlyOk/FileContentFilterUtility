package ru.shift.utility.statistic;

import ru.shift.utility.core.LineType;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * The Statistics class is responsible for collecting short statistics
 */
public class Statistics {
  /**
   * Number of rows of type {@code Integers}
   */
  protected int quantityIntegerLines;
  /**
   * Number of rows of type {@code Float}
   */
  protected int quantityFloatLines;
  /**
   * Number of rows of type {@code String}
   */
  protected int quantityStringLines;

  /**
   * Constructor of the Statistics class
   * This constructor sets the initial values to zero for the fields: {@link #quantityIntegerLines}, {@link #quantityFloatLines}, {@link #quantityIntegerLines}
   */
  public Statistics() {
    this.quantityIntegerLines = 0;
    this.quantityFloatLines = 0;
    this.quantityStringLines = 0;
  }

  /**
   * This method is responsible for updating statistics after processing a line
   * @param lineType line type according to {@link LineType} enumeration
   * @param line The line being processed
   */
  public void processLine(LineType lineType, String line) {
    if (LineType.INTEGER.equals(lineType)) updateIntegerLinesStatistics(new BigInteger(line));
    if (LineType.FLOAT.equals(lineType)) updateFloatLinesStatistics(new BigDecimal(line));
    if (LineType.STRING.equals(lineType)) updateStringLinesStatistics(line);
  }

  /**
   * The method updates statistics of type {@code Integer}
   * @param line The string being processed is converted to the type {@link BigInteger}
   */
  protected void updateIntegerLinesStatistics(BigInteger line) {
    this.quantityIntegerLines++;
  }

  /**
   * The method updates statistics of type {@code Float}
   * @param line The string being processed is converted to the type {@link BigDecimal}
   */
  protected void updateFloatLinesStatistics(BigDecimal line) {
    this.quantityFloatLines++;
  }

  /**
   * The method updates statistics of type {@code String}
   * @param line The line being processed
   */
  protected void updateStringLinesStatistics(String line) {
    this.quantityStringLines++;
  }

  /**
   * Getter {@link #quantityIntegerLines}
   * @return Number of {@code Integer} type rows
   */
  public int getQuantityIntegerLines() {
    return quantityIntegerLines;
  }

  /**
   * Getter {@link #quantityFloatLines}
   * @return Number of {@code Float} type rows
   */
  public int getQuantityFloatLines() {
    return quantityFloatLines;
  }

  /**
   * Getter {@link #quantityStringLines}
   * @return Number of {@code String} type rows
   */
  public int getQuantityStringLines() {
    return quantityStringLines;
  }

  /**
   * Method for printing statistics to console
   */
  public void printStatistics() {
    StatisticsPrinter.printShortStatistics(this);
  }
}
