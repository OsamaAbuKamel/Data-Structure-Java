package com.example.projectiii;

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

    public int getHeight() {
        return days.height();
    }

    public String traverseLevelOrder() {
        return days.traverseLevelOrder();
    }

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
        if (o instanceof Month) {
            Month month = (Month) o;
            return this.getMonth().compareTo(month.getMonth());
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
}