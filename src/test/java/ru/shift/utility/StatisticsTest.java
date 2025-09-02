package ru.shift.utility;

import org.junit.jupiter.api.Test;
import ru.shift.utility.core.LineType;
import ru.shift.utility.statistic.Statistics;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The {@link StatisticsTest} class is required to test the {@link Statistics} class
 */
public class StatisticsTest {

  /**
   * Test of method {@link Statistics#processLine(LineType, String)} of class {@link Statistics} when processing
   * lines of different types
   */
  @Test
  public void testUpdateStats() {
    Statistics statistics = new Statistics();

    statistics.processLine(LineType.INTEGER, "123");
    statistics.processLine(LineType.INTEGER, "-312312");
    statistics.processLine(LineType.FLOAT, "3.14");
    statistics.processLine(LineType.FLOAT, "-2.14321312312");
    statistics.processLine(LineType.STRING, "Lorem ipsum");
    statistics.processLine(LineType.STRING, "Short");
    statistics.processLine(LineType.STRING, "MaksFlyOk");

    assertEquals(2, statistics.getQuantityIntegerLines());
    assertEquals(2, statistics.getQuantityFloatLines());
    assertEquals(3, statistics.getQuantityStringLines());
  }
}
