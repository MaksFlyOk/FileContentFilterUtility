package ru.shift.utility.statistic;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

public class FullStatistic extends Statistics {
  private BigInteger minInteger;
  private BigInteger maxInteger;
  private BigInteger sumInteger;

  private BigDecimal minFloat;
  private BigDecimal maxFloat;
  private BigDecimal sumFloat;

  private int shortestStringLength;
  private int longestStringLength;

  public FullStatistic() {
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

  public BigInteger getMaxInteger() {
    return maxInteger;
  }

  public BigInteger getMinInteger() {
    return minInteger;
  }

  public BigInteger getSumInteger() {
    return sumInteger;
  }

  public BigDecimal getAverageInteger() {
    return quantityIntegerLines == 0 ? BigDecimal.ZERO : new BigDecimal(sumInteger).divide(BigDecimal.valueOf(quantityIntegerLines), 4, RoundingMode.HALF_EVEN);
  }

  public BigDecimal getMaxFloat() {
    return maxFloat;
  }

  public BigDecimal getMinFloat() {
    return minFloat;
  }

  public BigDecimal getSumFloat() {
    return sumFloat;
  }

  public BigDecimal getAverageFloat() {
    return quantityFloatLines == 0 ? BigDecimal.ZERO : sumFloat.divide(BigDecimal.valueOf(quantityFloatLines), 4, RoundingMode.HALF_EVEN);
  }

  public int getLongestStringLength() {
    return longestStringLength;
  }

  public int getShortestStringLength() {
    return shortestStringLength;
  }

  public void printStatistics() {
    StatisticsPrinter.printFullStatistics(this);
  }
}
