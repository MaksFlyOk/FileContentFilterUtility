package ru.shift.utility.statistic;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

/**
 * The FullStatistics class is responsible for collecting full statistics
 */
public class FullStatistics extends Statistics {
  /**
   * Minimum number of type {@code Integer}
   */
  private BigInteger minInteger;
  /**
   * Maximum number of type {@code Integer}
   */
  private BigInteger maxInteger;
  /**
   * Sum of all numbers of type {@code Integer}
   */
  private BigInteger sumInteger;

  /**
   * Minimum number of type {@code Float}
   */
  private BigDecimal minFloat;
  /**
   * Maximum number of type {@code Float}
   */
  private BigDecimal maxFloat;
  /**
   * Sum of all numbers of type {@code Float}
   */
  private BigDecimal sumFloat;

  /**
   * Number of characters in the shortest string of type {@code String}
   */
  private int shortestStringLength;
  /**
   * Number of characters in the longest string of type {@code String}
   */
  private int longestStringLength;

  /**
   * Constructor of the FullStatistics class
   * This constructor sets the initial values to zero for the fields: {@link #quantityIntegerLines},
   * {@link #quantityFloatLines}, {@link #quantityIntegerLines}
   */
  public FullStatistics() {
    super();
  }

  @Override
  protected void updateIntegerLinesStatistics (BigInteger line) {
    super.updateIntegerLinesStatistics(line);

    if (minInteger == null) {
      minInteger = line;
      maxInteger = line;
      sumInteger = line;
      return;
    }

    if (line.compareTo(maxInteger) > 0) maxInteger = line;
    if (line.compareTo(minInteger) < 0) minInteger = line;

    sumInteger = sumInteger.add(line);
  }

  @Override
  protected void updateFloatLinesStatistics(BigDecimal line) {
    super.updateFloatLinesStatistics(line);

    if (minFloat == null) {
      minFloat = line;
      maxFloat = line;
      sumFloat = line;
      return;
    }

    if (line.compareTo(maxFloat) > 0) maxFloat = line;
    if (line.compareTo(minFloat) < 0) minFloat = line;

    sumFloat = sumFloat.add(line);
  }

  @Override
  protected void updateStringLinesStatistics(String line) {
    super.updateStringLinesStatistics(line);

    if (shortestStringLength == 0) {
      shortestStringLength = line.length();
      longestStringLength = line.length();
      return;
    }

    if (shortestStringLength > line.length()) shortestStringLength = line.length();
    if (longestStringLength < line.length()) longestStringLength = line.length();
  }

  /**
   * Getter {@link #minInteger}
   * @return Minimum number of type {@code Integer}
   */
  public BigInteger getMinInteger() {
    return minInteger;
  }

  /**
   * Getter {@link #maxInteger}
   * @return Maximum number of type {@code Integer}
   */
  public BigInteger getMaxInteger() {
    return maxInteger;
  }

  /**
   * Getter {@link #sumInteger}
   * @return The sum of all numbers of type {@code Integer}
   */
  public BigInteger getSumInteger() {
    return sumInteger;
  }

  /**
   * This method calculates the average value of the {@code Integer} type
   * @return Average value of type {@code Integer}
   */
  public BigDecimal getAverageInteger() {
    return quantityIntegerLines == 0 ? BigDecimal.ZERO : new BigDecimal(sumInteger).divide(BigDecimal.valueOf(quantityIntegerLines), 4, RoundingMode.HALF_EVEN);
  }

  /**
   * Getter {@link #minFloat}
   * @return Minimum number of type {@code Float}
   */
  public BigDecimal getMinFloat() {
    return minFloat;
  }

  /**
   * Getter {@link #maxFloat}
   * @return Minimum number of type {@code Float}
   */
  public BigDecimal getMaxFloat() {
    return maxFloat;
  }

  /**
   * Getter {@link #sumFloat}
   * @return The sum of all numbers of type {@code Float}
   */
  public BigDecimal getSumFloat() {
    return sumFloat;
  }

  /**
   * This method calculates the average value of the {@code Float} type
   * @return Average value of type {@code Float}
   */
  public BigDecimal getAverageFloat() {
    return quantityFloatLines == 0 ? BigDecimal.ZERO : sumFloat.divide(BigDecimal.valueOf(quantityFloatLines), 4, RoundingMode.HALF_EVEN);
  }

  /**
   * Getter {@link #shortestStringLength}
   * @return Number of characters in the shortest string of type {@code String}
   */
  public int getShortestStringLength() {
    return shortestStringLength;
  }

  /**
   * Getter {@link #longestStringLength}
   * @return Number of characters in the longest string of type {@code String}
   */
  public int getLongestStringLength() {
    return longestStringLength;
  }

  @Override
  public void printStatistics() {
    StatisticsPrinter.printFullStatistics(this);
  }
}
