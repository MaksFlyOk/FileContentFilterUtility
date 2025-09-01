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
                    Number of rows of type \033[1mInteger\033[0m: %d
                    Number of rows of type \033[1mFloat\033[0m: %d
                    Number of rows of type \033[1mString\033[0m: %d
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
                    \033[1mInteger\033[0m: quantity = %d, min = %s, max = %s, sum = %s, avg = %s
                    \033[1mFloat\033[0m: quantity = %d, min = %s, max = %s, sum = %s, avg = %s
                    \033[1mString\033[0m: quantity = %d, shortest = %d, longest = %d
                    """,
            statistics.getQuantityIntegerLines(), statistics.getMinInteger(), statistics.getMaxInteger(), statistics.getSumInteger(), statistics.getAverageInteger(),
            statistics.getQuantityFloatLines(),statistics.getMinFloat(), statistics.getMaxFloat(), statistics.getSumFloat(), statistics.getAverageFloat(),
            statistics.getQuantityStringLines(), statistics.getShortestStringLength(), statistics.getLongestStringLength());
  }
}
