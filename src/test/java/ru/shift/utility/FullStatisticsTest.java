package ru.shift.utility;

import org.junit.jupiter.api.Test;
import ru.shift.utility.core.LineType;
import ru.shift.utility.statistic.FullStatistics;

import java.math.BigDecimal;
import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * The {@link FullStatisticsTest} class is required to test the {@link FullStatistics} class
 */
public class FullStatisticsTest {

  /**
   * Test of method {@link FullStatistics#processLine(LineType, String)} of class {@link FullStatistics} when
   * processing lines of type {@code Integer}
   */
  @Test
  public void testUpdateInteger() {
    FullStatistics statistics = new FullStatistics();

    statistics.processLine(LineType.INTEGER, "123");
    statistics.processLine(LineType.INTEGER, "-456");
    statistics.processLine(LineType.INTEGER, "789");

    assertEquals(3, statistics.getQuantityIntegerLines());

    assertEquals(new BigInteger("789"), statistics.getMaxInteger());
    assertEquals(new BigInteger("-456"), statistics.getMinInteger());
    assertEquals(new BigInteger("456"), statistics.getSumInteger());
    assertEquals(new BigDecimal("152.0000"), statistics.getAverageInteger());
  }

  /**
   * Test of method {@link FullStatistics#processLine(LineType, String)} of class {@link FullStatistics} when
   * processing lines of type {@code Float}
   */
  @Test
  public void testUpdateFloat() {
    FullStatistics statistics = new FullStatistics();

    statistics.processLine(LineType.FLOAT, "3.14");
    statistics.processLine(LineType.FLOAT, "-0.001");
    statistics.processLine(LineType.FLOAT, "1.3232E25");

    assertEquals(3, statistics.getQuantityFloatLines());
    assertEquals(new BigDecimal("1.3232E25"), statistics.getMaxFloat().stripTrailingZeros());
    assertEquals(new BigDecimal("-0.001"), statistics.getMinFloat());
    assertEquals(new BigDecimal("13232000000000000000000003.139"), statistics.getSumFloat());
    assertEquals(new BigDecimal("4410666666666666666666667.7130"), statistics.getAverageFloat());
  }

  /**
   * Test of method {@link FullStatistics#processLine(LineType, String)} of class {@link FullStatistics} when
   * processing lines of type {@code String}
   */
  @Test
  public void testUpdateString() {
    FullStatistics stats = new FullStatistics();

    stats.processLine(LineType.STRING, "Lorem ipsum");
    stats.processLine(LineType.STRING, "Short");
    stats.processLine(LineType.STRING, "A very long string example");

    assertEquals(3, stats.getQuantityStringLines());
    assertEquals(5, stats.getShortestStringLength());
    assertEquals(26, stats.getLongestStringLength());
  }
}