package ru.shift.utility.statistic;

public class StatisticsPrinter {
  static void printShortStatistics(Statistics statistics) {
    System.out.printf("""
                    Short statistics:
                    Number of rows of type [1mInteger[0m: %d
                    Number of rows of type [1mFloat[0m: %d
                    Number of rows of type [1mString[0m: %d
                    """,
            statistics.getQuantityIntegerLines(), statistics.getQuantityFloatLines(),statistics.getQuantityStringLines());
  }

  static void printFullStatistics(FullStatistic statistics) {
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
