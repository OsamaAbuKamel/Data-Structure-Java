package com.example.projectiii;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.Scanner;

public class RecordAVL {
    // list of years
    private AVL<Year> records;

    // constructor
    public RecordAVL() {
        records = new AVL<>();
    }

    public void add(ElectricityRecord record) {
        LocalDate date = record.getDate();
        // get year, month and day
        int year = date.getYear();
        String month = date.getMonth().toString();
        int day = date.getDayOfMonth();
        // check if year exists
        Year yearList = getYear(year);
        if (yearList == null) {
            // create a new object of year
            yearList = new Year(year);
            // add the year in the list
            records.insert(yearList);
        }
        // check if month exists
        Month monthList = yearList.search(month);
        if (monthList == null) {
            // create a new object of month
            monthList = new Month(month);
            // add the month th the yearList
            yearList.addMonth(monthList);
        }
        // check if days exists
        Day dayList = monthList.search(day);
        if (dayList == null) {
            // create a new object of day
            dayList = new Day(day, record);
            // add the day in the monthList
            monthList.addDay(dayList);
        } else {
            // if day already added
            throw new IllegalArgumentException("Day already exists");
        }
    }

    public void remove(LocalDate date) {
        // get year, month and day
        int year = date.getYear();
        String month = date.getMonth().toString();
        int day = date.getDayOfMonth();
        // search record in list
        ElectricityRecord record = search(date);
        if (record != null) {
            Year yearNode = getYear(year);
            if (yearNode != null) {
                Month monthNode = yearNode.search(month);
                if (monthNode != null) {
                    Day dayNode = monthNode.search(day);
                    if (dayNode != null) {
                        monthNode.removeDay(dayNode);
                    }
                    if (monthNode.getDays().isEmpty()) {
                        yearNode.removeMonth(monthNode);
                    }
                    if (yearNode.getMonthAVL().isEmpty()) {
                        records.remove(yearNode);
                    }
                }
            }
        } else
            // if record not found
            throw new IllegalArgumentException("Record does not exist");
    }

    public void update(ElectricityRecord newRecord) {
        // search record in list
        ElectricityRecord record = search(newRecord.getDate());
        // check if record exist
        if (record != null) {
            // update record
            record.setIsraeliLines(newRecord.getIsraeliLines());
            record.setGazaPowerPlant(newRecord.getGazaPowerPlant());
            record.setEgyptianLines(newRecord.getEgyptianLines());
            record.setOverallDemand(newRecord.getOverallDemand());
            record.setPowerCutsHoursDay(newRecord.getPowerCutsHoursDay());
            record.setTemp(newRecord.getTemp());
        } else {
            // if record not found
            throw new IllegalArgumentException("Record does not exist");
        }
    }

    public ElectricityRecord search(LocalDate date) {
        // get year, month and day
        int year = date.getYear();
        String month = date.getMonth().toString();
        int day = date.getDayOfMonth();
        // get year from list
        Year years = getYear(year);
        // check if years exist
        if (years != null) {
            // get month from year
            Month months = years.search(month);
            // check if month exist
            if (months != null) {
                // get day from month
                for (Day record : months.getDays()) {
                    if (record.getDay() == day) {
                        // return record
                        return record.getRecord();
                    }
                }
            }
        }
        // if record not found
        return null;
    }

    public Year getYear(int year) {
        // search year in list
        return records.search(new Year(year));
        // for (Year yearList : records) {
        // if (yearList.getYear() == year) {
        // return yearList;
        // }
        // }
    }

    public int getHeight() {
        return records.height();
    }

    public void loadFile(String fileName) {
        try (Scanner scanner = new Scanner(new File(fileName))) {
            String line = scanner.nextLine();
            while (scanner.hasNext()) {
                line = scanner.nextLine();
                String parts[] = line.split(",");
                String date = parts[0].trim();
                ElectricityRecord record = new ElectricityRecord(
                        LocalDate.parse(date),
                        Double.parseDouble(parts[1].trim()),
                        Double.parseDouble(parts[2].trim()),
                        Double.parseDouble(parts[3].trim()),
                        Double.parseDouble(parts[5].trim()),
                        Double.parseDouble(parts[6].trim()),
                        Double.parseDouble(parts[7].trim()));
                add(record);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void saveFile(String fileName) {
        try (
                PrintWriter writer = new PrintWriter(new FileOutputStream(fileName, true))) {
            for (Year year : records) {
                for (Month month : year.getMonthAVL()) {
                    for (Day record : month.getDays()) {
                        writer.write(record.toString());
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public void print() {
        System.out.println("Printing the list of records");
        for (Year record : records) {
            for (Month month : record.getMonthAVL()) {
                // System.out.println(month);
                for (Day day : month.getDays()) {
                    System.out.print(day);
                }
            }
        }
    }

    // setter and getter
    public AVL<Year> getRecords() {
        return this.records;
    }

    public void setRecords(AVL<Year> records) {
        this.records = records;
    }

    public int getMonthHeight(LocalDate date) {
        int y = date.getYear();
        Year year = getYear(y);
        if (year != null) {
            return year.getMonthAVL().height();
        }
        return 0;
    }

    public int getDayHeight(LocalDate date) {
        int y = date.getYear();
        String month = date.getMonth().toString();
        Year year = getYear(y);
        if (year != null) {
            Month month2 = year.search(month);
            if (month2 != null) {
                return month2.getDays().height();
            }
        }
        return 0;
    }

    @Override
    public String toString() {
        return "{" + " records='" + getRecords() + "'" + "}";
    }

    public static void main(String[] args) {
        RecordAVL list = new RecordAVL();
        String fileName = "C:\\Users\\osama\\DataStructure\\Data-Structure-\\ProjectIII\\src\\main\\resources\\com\\example\\projectiii\\Electricity.csv";
        // list.loadFile(fileName);
        // list.print();
        ElectricityRecord record = new ElectricityRecord(LocalDate.now(), 4, 4, 4, 4, 4, 4);
        ElectricityRecord record1 = new ElectricityRecord(LocalDate.of(2021, 2, 2), 4, 4, 4, 4, 4, 4);
        ElectricityRecord record2 = new ElectricityRecord(LocalDate.of(2021, 3, 2), 4, 4, 4, 4, 4, 4);
        ElectricityRecord record3 = new ElectricityRecord(LocalDate.of(2021, 4, 2), 4, 4, 4, 4, 4, 4);
        ElectricityRecord record4 = new ElectricityRecord(LocalDate.of(2021, 5, 2), 4, 4, 4, 4, 4, 4);
        ElectricityRecord record5 = new ElectricityRecord(LocalDate.of(2021, 6, 2), 4, 4, 4, 4, 4, 4);
        ElectricityRecord record6 = new ElectricityRecord(LocalDate.of(2021, 7, 2), 4, 4, 4, 4, 4, 4);
        ElectricityRecord record7 = new ElectricityRecord(LocalDate.of(2021, 8, 2), 4, 4, 4, 4, 4, 4);
        ElectricityRecord record8 = new ElectricityRecord(LocalDate.of(2021, 9, 2), 4, 4, 4, 4, 4, 4);
        ElectricityRecord record9 = new ElectricityRecord(LocalDate.of(2021, 10, 2), 4, 4, 4, 4, 4, 4);
        ElectricityRecord record10 = new ElectricityRecord(LocalDate.of(2021, 11, 2), 4, 4, 4, 4, 4, 4);
        ElectricityRecord record11 = new ElectricityRecord(LocalDate.of(2021, 12, 2), 4, 4, 4, 4, 4, 4);
        ElectricityRecord record12 = new ElectricityRecord(LocalDate.of(2021, 12, 3), 4, 4, 4, 4, 4, 4);
        list.add(record);
        list.add(record1);
        list.print();
        System.out.println("======================");
        list.remove(LocalDate.now());
        list.print();
        // list.add(record2);
        // list.add(record3);
        // list.add(record4);
        // list.add(record5);
        // list.add(record6);
        // list.add(record7);
        // list.add(record8);
        // list.add(record9);
        // list.add(record10);
        // list.add(record11);
        // list.add(record12);
        // System.out.println(list.getHeight());
        // System.out.println(list.getMonthHeight(2021));
        // System.out.println(list.getDayHeight(2021, "JULY"));
        // int h = list.getHeight();
        // int h1=0,h2=0;
        // for (Year year : list.records) {
        // h1 = year.getMonthAVL().height();
        // for (Month month : year.getMonthAVL()) {
        // h2 = month.getDays().height();
        // }
        // }
        // System.out.println("Years: "+h);
        // System.out.println("Months: "+h1);
        // System.out.println("Days: "+h2);
    }
}