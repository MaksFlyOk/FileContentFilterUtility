package ru.shift.utility.statistic;

import ru.shift.utility.core.LineType;

import java.math.BigDecimal;
import java.math.BigInteger;

public class Statistics {
  protected int quantityIntegerLines ;
  protected int quantityFloatLines;
  protected int quantityStringLines;

  public Statistics() {
    this.quantityIntegerLines = 0;
    this.quantityFloatLines = 0;
    this.quantityStringLines = 0;
  }

  public void processLine(LineType lineType, String line) {
    if (LineType.INTEGER.equals(lineType)) updateIntegerLinesStatistics(new BigInteger(line));
    if (LineType.FLOAT.equals(lineType)) updateFloatLinesStatistics(new BigDecimal(line));
    if (LineType.STRING.equals(lineType)) updateStringLinesStatistics(line);
  }

  protected void updateIntegerLinesStatistics(BigInteger line) {
    this.quantityIntegerLines++;
  }

  protected void updateFloatLinesStatistics(BigDecimal line) {
    this.quantityFloatLines++;
  }

  protected void updateStringLinesStatistics(String line) {
    this.quantityStringLines++;
  }

  public int getQuantityIntegerLines() {
    return quantityIntegerLines;
  }

  public int getQuantityFloatLines() {
    return quantityFloatLines;
  }

  public int getQuantityStringLines() {
    return quantityStringLines;
  }

  public void printStatistics() {
    StatisticsPrinter.printShortStatistics(this);
  }
}
