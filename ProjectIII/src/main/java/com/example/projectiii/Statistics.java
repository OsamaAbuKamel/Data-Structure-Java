package com.example.projectiii;

import java.time.LocalDate;

public class Statistics {
  private RecordAVL records;

  public Statistics(RecordAVL records) {
    this.records = records;
  }

  public double getStatisticForDay(
      int day,
      ElectricityType electricityType,
      StatisticType type) {
    // check if day is valid
    if (day < 1 || day > 31) {
      throw new IllegalArgumentException("Day must be between 1 and 31");
    } else {
      double total = 0;
      double count = 0;
      double max = 0;
      double min = Double.MAX_VALUE;
      AVL<Year> yearList = records.getRecords();
      // Loop through all the years
      for (Year year : yearList) {
        // Loop through all the months
        for (Month month : year.getMonthAVL()) {
          for (Day days : month.getDays()) {
            // check if the day in the list is the same as the day passed
            if (days.getDay() == day) {
              // get record value from method getRecord
              double recordValue = getRecord(electricityType, days.getRecord());
              // add value to total
              total += recordValue;
              // Increment count
              count++;
              // find max and min
              max = Math.max(max, recordValue);
              min = Math.min(min, recordValue);
            }
          }
        }
      }
      // return statistic
      return calcStatistic(type, total, count, max, min);
    }
  }

  public double getStatisticForMonth(
      Months month,
      ElectricityType electricityType,
      StatisticType type) {
    // month = month.toUpperCase();
    // if (!isValidMonth(month)) {
    // throw new IllegalArgumentException("Invalid month");
    // } else {
    double total = 0;
    double count = 0;
    double max = 0;
    double min = Double.MAX_VALUE;
    for (Year year : records.getRecords()) {
      for (Month months : year.getMonthAVL()) {
        if (months.getMonth().equals(month.toString())) {
          for (Day dayList : months.getDays()) {
            double recordValue = getRecord(electricityType, dayList.getRecord());
            total += recordValue;
            count++;
            max = Math.max(max, recordValue);
            min = Math.min(min, recordValue);
          }
        }
      }
    }
    return calcStatistic(type, total, count, max, min);
    // }
  }

  private boolean isValidMonth(String month) {
    return month.equals("JANUARY")
        || month.equals("FEBRUARY")
        || month.equals("MARCH")
        || month.equals("APRIL")
        || month.equals("MAY")
        || month.equals("JUNE")
        || month.equals("JULY")
        || month.equals("AUGUST")
        || month.equals("SEPTEMBER")
        || month.equals("OCTOBER")
        || month.equals("NOVEMBER")
        || month.equals("DECEMBER");
  }

  public double getStatisticForYear(
      int year,
      ElectricityType electricityType,
      StatisticType type) {
    // check if year is valid
    if (year < 0) {
      throw new IllegalArgumentException("Year cannot be negative");
    } else {
      double total = 0;
      double count = 0;
      double max = 0;
      double min = Double.MAX_VALUE;
      // get year from list
      Year yearList = records.getYear(year);
      AVL<Month> monthList = yearList.getMonthAVL();
      // loop through all the months
      for (Month months : monthList) {
        // loop through all the days
        for (Day dayList : months.getDays()) {
          // get record value from method getRecord
          double recordValue = getRecord(electricityType, dayList.getRecord());
          // add value to total
          total += recordValue;
          // increment count
          count++;
          // find max and min
          max = Math.max(max, recordValue);
          min = Math.min(min, recordValue);
        }
      }
      // return statistic
      return calcStatistic(type, total, count, max, min);
    }
  }

  private double calcStatistic(
      StatisticType type,
      double total,
      double count,
      double max,
      double min) {
    // return statistic
    switch (type) {
      case TOTAL:
        return total;
      case AVERAGE:
        double avg = count == 0 ? 0 : total / count;
        return avg;
      case MAX:
        return max;
      case MIN:
        return min;
      default:
        return 0;
    }
  }

  private double getRecord(ElectricityType type, ElectricityRecord record) {
    // return record value
    switch (type) {
      case ISRAELI_LINES:
        return record.getIsraeliLines();
      case EGYPTIAN_LINES:
        return record.getEgyptianLines();
      case GAZA_POWER:
        return record.getGazaPowerPlant();
      case POWER_CUTS:
        return record.getPowerCutsHoursDay();
      case TOTAL_SUPPLY:
        return record.getTotalSupply();
      case TEMP:
        return record.getTemp();
      default:
        return Double.NaN;
    }
  }

  public static void main(String[] args) {
    RecordAVL list = new RecordAVL();
    // Create an instance of the class
    Statistics statistics = new Statistics(list);
    ElectricityRecord record = new ElectricityRecord(LocalDate.now(), 4, 4, 4, 4, 4, 4);
    list.add(record);
    double max = statistics.getStatisticForMonth(Months.JANUARY, ElectricityType.GAZA_POWER, StatisticType.TOTAL);
    // double gaza = statistics.getRecord(ElectricityType.GAZA_POWER, record);
    System.out.println(max);
  }
}
