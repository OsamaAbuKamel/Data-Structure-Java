package com.example.projectiii;

public class Year implements Comparable<Year>, Cloneable {
    private int year;
    private AVL<Month> monthAVL;

    public Year() {
        monthAVL = new AVL<>();
    }

    public Year(int year) {
        this.year = year;
        monthAVL = new AVL<>();
    }

    public void addMonth(Month month) {
        this.monthAVL.insert(month);
    }

    public void removeMonth(Month month) {
        this.monthAVL.delete(month);
    }

    public Month search(String month) {
        return monthAVL.search(new Month(month));
    }

    public int getHeight() {
        return monthAVL.height();
    }

    public Month get(String index) {
        for (Month month : monthAVL) {
            if (month.getMonth().equals(index)) {
                return month;
            }
        }
        return null;
    }

    public AVL<Month> getMonthAVL() {
        return monthAVL;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String toString() {
        return year + " " + monthAVL.toString();
    }

    @Override
    public int compareTo(Year o) {
        return Integer.compare(year, o.year);
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
        if (o instanceof Year year) {
            return year.getYear() == this.getYear();
        }
        return false;
    }
}