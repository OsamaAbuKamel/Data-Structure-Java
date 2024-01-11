package com.example.projectiii;


public class Day implements Comparable<Day>, Cloneable {
    private int day;
    private ElectricityRecord record;

    public Day() {
    }

    public Day(int day, ElectricityRecord record) {
        this.day = day;
        this.record = record;
    }

    public int getDay() {
        return this.day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public ElectricityRecord getRecord() {
        return this.record;
    }

    public void setRecord(ElectricityRecord record) {
        this.record = record;
    }

    @Override
    public int compareTo(Day o) {
        return Integer.compare(day, o.day);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (o instanceof Day day) {
            return day.getDay() == this.getDay();
        }
        return false;
    }

    @Override
    protected Object clone()
            throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return "" + getRecord();
    }

}