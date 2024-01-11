package com.example.projectiii;

import java.time.LocalDate;

public class Month implements Comparable<Month>, Cloneable {
    private String month;
    private AVL<Day> days;

    public Month(String month) {
        this.month = month;
        days = new AVL<>();
    }

    public void addDay(Day day) {
        days.insert(day);
    }

    public void removeDay(Day day) {
        days.delete(day);
    }

    public Day search(int day) {
        return days.search(new Day(day, null));
    }
    public int getHeight(){
        return days.height();
    }
    public String traverseLevelOrder() {
        return days.traverseLevelOrder();
        
    }

    // public Day get(int index) {
    // for (Day day : days) {
    // if (day.getDay() == index) {
    // return day;
    // }
    // }
    // return null;
    // }
    public String getMonth() {
        return month;
    }

    public AVL<Day> getDays() {
        return days;
    }

    public String toString() {
        return month + " " + days.toString();
    }


    @Override
    public int compareTo(Month o) {
        if (o == this)
            return 0;
        if (o instanceof Month month) {
            return month.getMonth().compareTo(this.getMonth());
        } else {
            return -1;
        }
    }

    @Override
    protected Object clone()
            throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (o instanceof Month month) {
            return month.getMonth() == this.getMonth();
        }
        return false;
    }

    public static void main(String[] args) {
        Month month = new Month(LocalDate.now().getMonth().toString());
        month.addDay(new Day(1, new ElectricityRecord(LocalDate.of(2021, 1, 1),4,4,4,4,4,4)));
        month.addDay(new Day(1, new ElectricityRecord(LocalDate.of(2022, 1, 1),4,4,4,4,4,4)));
        month.addDay(new Day(1, new ElectricityRecord(LocalDate.of(2023, 1, 1),4,4,4,4,4,4)));
        month.addDay(new Day(1, new ElectricityRecord(LocalDate.now(),4,4,4,4,4,4)));
        // days.insert(new Day(2, null));
        // days.insert(new Day(3, null));
        // days.insert(new Day(4, null));
        // days.insert(new Day(5, null));
        // days.insert(new Day(6, null));
        // days.insert(new Day(7, null));
        // days.insert(new Day(8, null));
        // days.insert(new Day(9, null));
        // days.insert(new Day(10, null));
        System.out.println(month.traverseLevelOrder());
    }
}