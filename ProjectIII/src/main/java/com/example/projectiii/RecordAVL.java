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
        Year yearNode = getYear(year);
        if (yearNode == null) {
            // create a new object of year
            yearNode = new Year(year);
            // add the year in the list
            records.insert(yearNode);
        }
        // check if month exists
        Month monthNode = yearNode.search(month);
        if (monthNode == null) {
            // create a new object of month
            monthNode = new Month(month);
            // add the month th the yearList
            yearNode.addMonth(monthNode);
        }
        // check if days exists
        Day dayNode = monthNode.search(day);
        if (dayNode == null) {
            // create a new object of day
            dayNode = new Day(day, record);
            // add the day in the monthList
            monthNode.addDay(dayNode);
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
                Month montNode = yearNode.search(month);
                if (montNode != null) {
                    Day dayNode = montNode.search(day);
                    if (dayNode != null)
                        montNode.removeDay(dayNode);
                }
                if (montNode.getDays().isEmpty())
                    yearNode.removeMonth(montNode);
            }
            if (yearNode.getMonthAVL().isEmpty())
                records.delete(yearNode);
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
}