package ru.shift.utility.statistic;

/**
 * The Statistics Printer class is responsible for outputting statistics to the console
 */
public class StatisticsPrinter {
  /**
   * The method generates and outputs short statistics
   * @param statistics Instance of the {@link Statistics} class
   */
  static void printShortStatistics(Statistics statistics) {
    System.out.printf("""
                    Short statistics:
                    Number of rows of type [1mInteger[0m: %d
                    Number of rows of type [1mFloat[0m: %d
                    Number of rows of type [1mString[0m: %d
                    """,
            statistics.getQuantityIntegerLines(), statistics.getQuantityFloatLines(),statistics.getQuantityStringLines());
  }

  /**
   * The method generates and outputs full statistics
   * @param statistics Instance of the {@link FullStatistics} class
   */
  static void printFullStatistics(FullStatistics statistics) {
    System.out.printf("""
                    Full statistics:
                    [1mInteger[0m: min = %s, max = %s, sum = %s, avg = %s
                    [1mFloat[0m: min = %s, max = %s, sum = %s, avg = %s
                    [1mString[0m: shortest = %d, longest = %d
                    """,
            statistics.getMinInteger(), statistics.getMaxInteger(), statistics.getSumInteger(), statistics.getAverageInteger(),
            statistics.getMinFloat(), statistics.getMaxFloat(), statistics.getSumFloat(), statistics.getAverageFloat(),
            statistics.getShortestStringLength(), statistics.getLongestStringLength());
  }
}
